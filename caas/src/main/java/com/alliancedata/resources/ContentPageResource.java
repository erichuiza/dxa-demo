package com.alliancedata.resources;

import com.alliancedata.model.AbstractModel;
import com.alliancedata.model.Page;
import com.alliancedata.model.Promo;
import com.alliancedata.model.entity.POCPromo;
import com.sdl.webapp.common.api.WebRequestContext;
import com.sdl.webapp.common.api.content.ContentProvider;
import com.sdl.webapp.common.api.content.ContentProviderException;
import com.sdl.webapp.common.api.content.PageNotFoundException;
import com.sdl.webapp.common.api.formats.DataFormatter;
import com.sdl.webapp.common.api.localization.Localization;
import com.sdl.webapp.common.api.model.EntityModel;
import com.sdl.webapp.common.api.model.PageModel;
import com.sdl.webapp.common.api.model.RegionModel;
import com.sdl.webapp.common.controller.BaseController;
import com.sdl.webapp.common.controller.exception.InternalServerErrorException;
import com.sdl.webapp.common.controller.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/caas")
public class ContentPageResource extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ContentPageResource.class);

    @Autowired
    private ContentProvider contentProvider;
    @Autowired
    private WebRequestContext webRequestContext;
    @Autowired
    private DataFormatter dataFormatters;

    public ContentPageResource() {
    }

    @RequestMapping(
        value = { "/**" },
        params = { "url", "format" },
        produces = { "application/json" }
    )
    public ModelAndView handlePageByUrl(
            @RequestParam( value = "url") String url,
            HttpServletRequest request) {

        Localization localization = this.webRequestContext.getLocalization();
        PageModel page = this.getPageModel(url, localization);

        this.enrichEmbeddedModels(page, request);

        ModelMapper modelMapper = new ModelMapper();
        Page modelPage = modelMapper.map(page, Page.class);

        List<AbstractModel> presentations = new ArrayList<AbstractModel>();
        if (page.getRegions() != null && page.containsRegion("Main")) {
            RegionModel mainRegion = page.getRegions().get("Main");

            List<EntityModel> entities = mainRegion.getEntities();
            for (EntityModel entityModel : entities) {
                if (entityModel instanceof POCPromo) {
                    Promo promo = modelMapper.map(entityModel, Promo.class);
                    presentations.add(promo);
                }
            }
        }
        modelPage.setPresentations(presentations);

        return this.dataFormatters.view(modelPage);
    }

    private PageModel getPageModel(String path, Localization localization) {
        try {
            return this.contentProvider.getPageModel(path, localization);
        } catch (PageNotFoundException e) {
            throw new NotFoundException("Page not found: " + path, e);
        } catch (ContentProviderException e) {
            throw new InternalServerErrorException("An unexpected error occurred", e);
        }
    }

    private void enrichEmbeddedModels(PageModel model, HttpServletRequest request) {
        if (model != null) {
            Iterator r = model.getRegions().iterator();

            while(r.hasNext()) {
                RegionModel region = (RegionModel)r.next();

                for(int i = 0; i < region.getEntities().size(); ++i) {
                    EntityModel entity = region.getEntities().get(i);
                    if (entity != null && entity.getMvcData() != null) {
                        region.getEntities().set(i, this.enrichEntityModel(entity, request));
                    }
                }
            }
        }
    }
}

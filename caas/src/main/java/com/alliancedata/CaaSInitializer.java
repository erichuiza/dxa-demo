package com.alliancedata;

import com.alliancedata.model.entity.POCImage;
import com.alliancedata.model.entity.POCPage;
import com.alliancedata.model.entity.POCPromo;
import com.sdl.webapp.common.api.mapping.views.AbstractInitializer;
import com.sdl.webapp.common.api.mapping.views.ModuleInfo;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModel;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModels;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = { "com.alliancedata" })
public class CaaSInitializer  {
    @RegisteredViewModels({
            @RegisteredViewModel(viewName = "Image", modelClass = POCImage.class),
            @RegisteredViewModel(viewName = "Promo", modelClass = POCPromo.class),
            @RegisteredViewModel(viewName = "GeneralPage", modelClass = POCPage.class)
    })
    @Component
    @ModuleInfo(name = "CaaS module", areaName = "caas", description = "CaaS Module")
    public static class CaaSViewInitializer extends AbstractInitializer {
        @Override
        protected String getAreaName() {
            return "caas";
        }
    }
}
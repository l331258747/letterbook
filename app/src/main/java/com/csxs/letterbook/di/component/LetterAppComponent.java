package com.csxs.letterbook.di.component;


import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.di.modules.ActivityModules;
import com.csxs.letterbook.di.modules.AppModules;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        AppModules.class,
        ActivityModules.class})
public interface LetterAppComponent
        extends AndroidInjector<LetterApp> {
    @Component.Factory
    interface Factory {
        LetterAppComponent create(@BindsInstance LetterApp myApplication);
    }
}

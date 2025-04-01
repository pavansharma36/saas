package io.github.pavansharma36.core.common.listener;

import org.springframework.context.ApplicationContext;

public interface AppLoaderListener {

  void onStart(ApplicationContext applicationContext);

  void onStop(ApplicationContext applicationContext);

}

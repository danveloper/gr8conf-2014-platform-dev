package gr8conf.meta.annotations

import groovy.transform.AnnotationCollector
import org.springframework.web.bind.annotation.RequestMapping

@AnnotationCollector(value = RequestMapping, processor = "gr8conf.ConfigurationControllerAnnotationProcessor")
@interface BootApp {
}
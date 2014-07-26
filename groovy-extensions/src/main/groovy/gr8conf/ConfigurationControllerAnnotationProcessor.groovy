package gr8conf

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AnnotationCollectorTransform
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class ConfigurationControllerAnnotationProcessor extends AnnotationCollectorTransform {
  private static final ClassNode CONFIGURATION_NODE = ClassHelper.make(Configuration)
  private static final ClassNode ENABLE_AUTO_CONFIGURE_NODE = ClassHelper.make(EnableAutoConfiguration)
  private static final ClassNode COMPONENT_SCAN_NODE = ClassHelper.make(ComponentScan)
  private static final ClassNode REST_CONTROLLER_NODE = ClassHelper.make(RestController)
  private static final ClassNode REQUEST_MAPPING_NODE = ClassHelper.make(RequestMapping)
  private static final ClassNode COMPILE_STATIC_NODE = ClassHelper.make(CompileStatic)

  List<AnnotationNode> visit(AnnotationNode collector, AnnotationNode usage,
                             AnnotatedNode annotated, SourceUnit src) {

    AnnotationNode configurationNode = new AnnotationNode(CONFIGURATION_NODE)
    AnnotationNode enableAutoConfigureNode = new AnnotationNode(ENABLE_AUTO_CONFIGURE_NODE)
    AnnotationNode componentScanNode = new AnnotationNode(COMPONENT_SCAN_NODE)
    AnnotationNode restControllerNode = new AnnotationNode(REST_CONTROLLER_NODE)
    AnnotationNode requestMappingNode = new AnnotationNode(REQUEST_MAPPING_NODE)
    AnnotationNode compileStaticNode = new AnnotationNode(COMPILE_STATIC_NODE)

    Expression path = usage.getMember("value")
    requestMappingNode.addMember("value", path)
    requestMappingNode.addMember("produces", new ConstantExpression("application/json"))

    [configurationNode, enableAutoConfigureNode, componentScanNode, requestMappingNode, compileStaticNode, restControllerNode]
  }
}

package com.overstock.constraint.processor;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;

import org.junit.Test;

import com.overstock.constraint.provider.ConstraintProvider;

public class ConstraintsTest {

  @Test
  public void constraintCaching() {
    AnnotationMirror annotation = mock(AnnotationMirror.class);

    DeclaredType declaredType = mock(DeclaredType.class);
    when(annotation.getAnnotationType()).thenReturn(declaredType);
    Element element = mock(Element.class);
    when(declaredType.asElement()).thenReturn(element);

    ProcessingEnvironment env = mock(ProcessingEnvironment.class);
    Elements elementUtils = mock(Elements.class);
    when(env.getElementUtils()).thenReturn(elementUtils);
    TypeElement typeElement = mock(TypeElement.class);
    when(elementUtils.getTypeElement(anyString())).thenReturn(typeElement);

    ExternalConstraints externalConstraints = ExternalConstraints.from(
      Collections.<ConstraintProvider>emptyList(), env);
    InternalConstraints internalConstraints = InternalConstraints.from(Collections.<Element>emptySet(), env);
    assertSame(Constraints.on(annotation, externalConstraints, internalConstraints, env),
      Constraints.on(annotation, externalConstraints, internalConstraints, env));
  }

}

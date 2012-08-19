/**
 */
package ch.vorburger.vaadin.designer.samplescreen;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage
 * @generated
 */
public interface SamplescreenFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SamplescreenFactory eINSTANCE = ch.vorburger.vaadin.designer.samplescreen.impl.SamplescreenFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Screen</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Screen</em>'.
   * @generated
   */
  Screen createScreen();

  /**
   * Returns a new object of class '<em>Field</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Field</em>'.
   * @generated
   */
  Field createField();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SamplescreenPackage getSamplescreenPackage();

} //SamplescreenFactory

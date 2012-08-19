/**
 */
package ch.vorburger.vaadin.designer.samplescreen.impl;

import ch.vorburger.vaadin.designer.samplescreen.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SamplescreenFactoryImpl extends EFactoryImpl implements SamplescreenFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SamplescreenFactory init()
  {
    try
    {
      SamplescreenFactory theSamplescreenFactory = (SamplescreenFactory)EPackage.Registry.INSTANCE.getEFactory("ch.vorburger.vaadin.designer.samplescreen"); 
      if (theSamplescreenFactory != null)
      {
        return theSamplescreenFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SamplescreenFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SamplescreenFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SamplescreenPackage.SCREEN: return createScreen();
      case SamplescreenPackage.FIELD: return createField();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Screen createScreen()
  {
    ScreenImpl screen = new ScreenImpl();
    return screen;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Field createField()
  {
    FieldImpl field = new FieldImpl();
    return field;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SamplescreenPackage getSamplescreenPackage()
  {
    return (SamplescreenPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SamplescreenPackage getPackage()
  {
    return SamplescreenPackage.eINSTANCE;
  }

} //SamplescreenFactoryImpl

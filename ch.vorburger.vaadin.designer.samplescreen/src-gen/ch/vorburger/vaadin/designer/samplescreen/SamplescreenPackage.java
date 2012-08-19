/**
 */
package ch.vorburger.vaadin.designer.samplescreen;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel basePackage='ch.vorburger.vaadin.designer'"
 * @generated
 */
public interface SamplescreenPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "samplescreen";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "ch.vorburger.vaadin.designer.samplescreen";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "samplescreen";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SamplescreenPackage eINSTANCE = ch.vorburger.vaadin.designer.samplescreen.impl.SamplescreenPackageImpl.init();

  /**
   * The meta object id for the '{@link ch.vorburger.vaadin.designer.samplescreen.impl.ScreenImpl <em>Screen</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see ch.vorburger.vaadin.designer.samplescreen.impl.ScreenImpl
   * @see ch.vorburger.vaadin.designer.samplescreen.impl.SamplescreenPackageImpl#getScreen()
   * @generated
   */
  int SCREEN = 0;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCREEN__TITLE = 0;

  /**
   * The feature id for the '<em><b>Fields</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCREEN__FIELDS = 1;

  /**
   * The number of structural features of the '<em>Screen</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCREEN_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Screen</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SCREEN_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link ch.vorburger.vaadin.designer.samplescreen.impl.FieldImpl <em>Field</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see ch.vorburger.vaadin.designer.samplescreen.impl.FieldImpl
   * @see ch.vorburger.vaadin.designer.samplescreen.impl.SamplescreenPackageImpl#getField()
   * @generated
   */
  int FIELD = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD__NAME = 0;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD__LABEL = 1;

  /**
   * The feature id for the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD__X = 2;

  /**
   * The feature id for the '<em><b>Y</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD__Y = 3;

  /**
   * The number of structural features of the '<em>Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Field</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FIELD_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link ch.vorburger.vaadin.designer.samplescreen.Screen <em>Screen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Screen</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Screen
   * @generated
   */
  EClass getScreen();

  /**
   * Returns the meta object for the attribute '{@link ch.vorburger.vaadin.designer.samplescreen.Screen#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Screen#getTitle()
   * @see #getScreen()
   * @generated
   */
  EAttribute getScreen_Title();

  /**
   * Returns the meta object for the containment reference list '{@link ch.vorburger.vaadin.designer.samplescreen.Screen#getFields <em>Fields</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fields</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Screen#getFields()
   * @see #getScreen()
   * @generated
   */
  EReference getScreen_Fields();

  /**
   * Returns the meta object for class '{@link ch.vorburger.vaadin.designer.samplescreen.Field <em>Field</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Field</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Field
   * @generated
   */
  EClass getField();

  /**
   * Returns the meta object for the attribute '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Field#getName()
   * @see #getField()
   * @generated
   */
  EAttribute getField_Name();

  /**
   * Returns the meta object for the attribute '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Field#getLabel()
   * @see #getField()
   * @generated
   */
  EAttribute getField_Label();

  /**
   * Returns the meta object for the attribute '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getX <em>X</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>X</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Field#getX()
   * @see #getField()
   * @generated
   */
  EAttribute getField_X();

  /**
   * Returns the meta object for the attribute '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getY <em>Y</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Y</em>'.
   * @see ch.vorburger.vaadin.designer.samplescreen.Field#getY()
   * @see #getField()
   * @generated
   */
  EAttribute getField_Y();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SamplescreenFactory getSamplescreenFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link ch.vorburger.vaadin.designer.samplescreen.impl.ScreenImpl <em>Screen</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ch.vorburger.vaadin.designer.samplescreen.impl.ScreenImpl
     * @see ch.vorburger.vaadin.designer.samplescreen.impl.SamplescreenPackageImpl#getScreen()
     * @generated
     */
    EClass SCREEN = eINSTANCE.getScreen();

    /**
     * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SCREEN__TITLE = eINSTANCE.getScreen_Title();

    /**
     * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SCREEN__FIELDS = eINSTANCE.getScreen_Fields();

    /**
     * The meta object literal for the '{@link ch.vorburger.vaadin.designer.samplescreen.impl.FieldImpl <em>Field</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ch.vorburger.vaadin.designer.samplescreen.impl.FieldImpl
     * @see ch.vorburger.vaadin.designer.samplescreen.impl.SamplescreenPackageImpl#getField()
     * @generated
     */
    EClass FIELD = eINSTANCE.getField();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FIELD__NAME = eINSTANCE.getField_Name();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FIELD__LABEL = eINSTANCE.getField_Label();

    /**
     * The meta object literal for the '<em><b>X</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FIELD__X = eINSTANCE.getField_X();

    /**
     * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FIELD__Y = eINSTANCE.getField_Y();

  }

} //SamplescreenPackage

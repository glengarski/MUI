/**
 */
package ch.vorburger.vaadin.designer.samplescreen;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ch.vorburger.vaadin.designer.samplescreen.Field#getName <em>Name</em>}</li>
 *   <li>{@link ch.vorburger.vaadin.designer.samplescreen.Field#getLabel <em>Label</em>}</li>
 *   <li>{@link ch.vorburger.vaadin.designer.samplescreen.Field#getX <em>X</em>}</li>
 *   <li>{@link ch.vorburger.vaadin.designer.samplescreen.Field#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getField()
 * @model
 * @generated
 */
public interface Field extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getField_Name()
   * @model unique="false" id="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getField_Label()
   * @model unique="false"
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>X</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>X</em>' attribute.
   * @see #setX(int)
   * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getField_X()
   * @model unique="false"
   * @generated
   */
  int getX();

  /**
   * Sets the value of the '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getX <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>X</em>' attribute.
   * @see #getX()
   * @generated
   */
  void setX(int value);

  /**
   * Returns the value of the '<em><b>Y</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Y</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Y</em>' attribute.
   * @see #setY(int)
   * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getField_Y()
   * @model unique="false"
   * @generated
   */
  int getY();

  /**
   * Sets the value of the '{@link ch.vorburger.vaadin.designer.samplescreen.Field#getY <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Y</em>' attribute.
   * @see #getY()
   * @generated
   */
  void setY(int value);

} // Field

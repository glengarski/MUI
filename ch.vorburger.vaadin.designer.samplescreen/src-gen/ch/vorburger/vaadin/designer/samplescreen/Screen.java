/**
 */
package ch.vorburger.vaadin.designer.samplescreen;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Screen</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ch.vorburger.vaadin.designer.samplescreen.Screen#getTitle <em>Title</em>}</li>
 *   <li>{@link ch.vorburger.vaadin.designer.samplescreen.Screen#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getScreen()
 * @model
 * @generated
 */
public interface Screen extends EObject
{
  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Title</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getScreen_Title()
   * @model unique="false"
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link ch.vorburger.vaadin.designer.samplescreen.Screen#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
   * The list contents are of type {@link ch.vorburger.vaadin.designer.samplescreen.Field}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fields</em>' containment reference list.
   * @see ch.vorburger.vaadin.designer.samplescreen.SamplescreenPackage#getScreen_Fields()
   * @model containment="true"
   * @generated
   */
  EList<Field> getFields();

} // Screen

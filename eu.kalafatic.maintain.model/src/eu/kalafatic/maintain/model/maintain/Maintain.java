/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.kalafatic.maintain.model.maintain;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Maintain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.kalafatic.maintain.model.maintain.Maintain#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.kalafatic.maintain.model.maintain.MaintainPackage#getMaintain()
 * @model
 * @generated
 */
public interface Maintain extends EObject {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' map.
	 * @see #setChildren(Map.Entry)
	 * @see eu.kalafatic.maintain.model.maintain.MaintainPackage#getMaintain_Children()
	 * @model mapType="eu.kalafatic.maintain.model.maintain.StringToNodeMapEntry<org.eclipse.emf.ecore.EString, eu.kalafatic.maintain.model.maintain.Node>"
	 * @generated
	 */
	Map.Entry<String, Node> getChildren();

	/**
	 * Sets the value of the '{@link eu.kalafatic.maintain.model.maintain.Maintain#getChildren <em>Children</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Children</em>' map.
	 * @see #getChildren()
	 * @generated
	 */
	void setChildren(Map.Entry<String, Node> value);

} // Maintain

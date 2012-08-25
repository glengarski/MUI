package ch.vorburger.vaadin.designer.samplescreen;

import ch.vorburger.vaadin.beans.dragdroplayouts.AbsoluteNotifyingComponentPosition;
import ch.vorburger.vaadin.beans.dragdroplayouts.DDAbsoluteLayoutWithBeanPositionChange;
import ch.vorburger.vaadin.designer.samplescreen.Field;
import ch.vorburger.vaadin.designer.samplescreen.SampleFixedScreenComponent;
import ch.vorburger.vaadin.designer.samplescreen.Screen;
import com.vaadin.ui.TextField;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * Dream Version of how SampleFixedScreenBinding should ideally be written,
 * in a TBD Xtend-based DSL which directly supports bidi DataBinding,
 * via a proposed "<=>" operator.
 * 
 * @author Michael Vorburger
 */
@SuppressWarnings("all")
public class SampleFixedScreenBindingDream {
  public void bind(final SampleFixedScreenComponent ui, final Screen model) {
    EList<Field> _fields = model.getFields();
    final Procedure1<Field> _function = new Procedure1<Field>() {
        public void apply(final Field field) {
          TextField _textField = new TextField();
          final TextField widget = _textField;
          AbsoluteNotifyingComponentPosition _absoluteNotifyingComponentPosition = new AbsoluteNotifyingComponentPosition();
          final AbsoluteNotifyingComponentPosition position = _absoluteNotifyingComponentPosition;
          String _label = field.getLabel();
          widget.setCaption(_label);
          DDAbsoluteLayoutWithBeanPositionChange _absoluteLayout = ui.getAbsoluteLayout();
          _absoluteLayout.addComponent(widget, position);
        }
      };
    IterableExtensions.<Field>forEach(_fields, _function);
  }
}

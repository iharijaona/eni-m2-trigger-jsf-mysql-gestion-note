package jsf;

import jpa.entities.Note;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.NoteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Harijaona Ravelondrina <hravelondrina@gmail.com>
 */
@Named("noteController")
@SessionScoped
public class NoteController implements Serializable {

    @EJB
    private jpa.session.NoteFacade ejbFacade;
    private List<Note> items = null;
    private Note selected;

    public NoteController() {
    }

    public Note getSelected() {
        return selected;
    }

    public void setSelected(Note selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getNotePK().setNumetudiant(selected.getEtudiant().getNumetudiant());
        selected.getNotePK().setNummatiere(selected.getMatiere().getNummatiere());
    }

    protected void initializeEmbeddableKey() {
        selected.setNotePK(new jpa.entities.NotePK());
    }

    private NoteFacade getFacade() {
        return ejbFacade;
    }

    public Note prepareCreate() {
        selected = new Note();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("NoteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("NoteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("NoteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Note> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        else {
            getFacade().flush();
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Note getNote(jpa.entities.NotePK id) {
        return getFacade().find(id);
    }

    public List<Note> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Note> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Note.class)
    public static class NoteControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NoteController controller = (NoteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "noteController");
            return controller.getNote(getKey(value));
        }

        jpa.entities.NotePK getKey(String value) {
            jpa.entities.NotePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.NotePK();
            key.setNumetudiant(Integer.parseInt(values[0]));
            key.setNummatiere(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.NotePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getNumetudiant());
            sb.append(SEPARATOR);
            sb.append(value.getNummatiere());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Note) {
                Note o = (Note) object;
                return getStringKey(o.getNotePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Note.class.getName()});
                return null;
            }
        }

    }

}

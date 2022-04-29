package jsf;

import jpa.entities.Etudiant;
import jsf.util.JsfUtil;
import jsf.util.JsfUtil.PersistAction;
import jpa.session.EtudiantFacade;

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
@Named("etudiantController")
@SessionScoped
public class EtudiantController implements Serializable {

    @EJB
    private jpa.session.EtudiantFacade ejbFacade;
    private List<Etudiant> items = null;
    private Etudiant selected;

    public EtudiantController() {
    }

    public Etudiant getSelected() {
        return selected;
    }

    public void setSelected(Etudiant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EtudiantFacade getFacade() {
        return ejbFacade;
    }

    public Etudiant prepareCreate() {
        selected = new Etudiant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("EtudiantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("EtudiantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("EtudiantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Etudiant> getItems() {
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

    public Etudiant getEtudiant(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Etudiant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Etudiant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Etudiant.class)
    public static class EtudiantControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EtudiantController controller = (EtudiantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "etudiantController");
            return controller.getEtudiant(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Etudiant) {
                Etudiant o = (Etudiant) object;
                return getStringKey(o.getNumetudiant());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Etudiant.class.getName()});
                return null;
            }
        }

    }

}

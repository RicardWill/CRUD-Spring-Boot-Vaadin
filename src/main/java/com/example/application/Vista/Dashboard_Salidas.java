package com.example.application.Vista;


import com.example.application.Persistencia.Entidad.Entrada;
import com.example.application.Persistencia.Entidad.Salida;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.events.ChartLoadEvent;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


import java.time.LocalDateTime;
import java.time.LocalTime;

public class Dashboard_Salidas extends FormLayout{

    //Mandar los datos al formulario o menu
    Binder<Salida> binder = new BeanValidationBinder<>(Salida.class);
    TextField claveEmpleado = new TextField("Clave de empleado");
    //DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
    //DatePicker fechaEntrada = new DatePicker("Fecha de registro");
    LocalDateTime locaDate = LocalDateTime.now();

    String dia = String.valueOf(locaDate.getDayOfMonth());
    String mes = String.valueOf(locaDate.getMonth());
    String anio = String.valueOf(locaDate.getYear());
    ComboBox<String> fechaSalida = new ComboBox<>("Fecha salida",dia.concat("/").concat(mes).concat("/").concat(anio));
    //TimePicker horaEntrada = new TimePicker();
    String segundos = String.valueOf(locaDate.getSecond());
    String minutos = String.valueOf(locaDate.getMinute());
    String hours  = String.valueOf(locaDate.getHour());

    ComboBox<String> horaSalida = new ComboBox<>("Hora de salida",hours.concat(":").concat(minutos).concat(":").concat(segundos));
    ComboBox<String> identificadorKiosko = new ComboBox<>("Identificador kiosko");

    //Botones de instruccion
    Button registrar = new Button("Registrar");
    Button eliminar = new Button("Eliminar");
    Button cerrar = new Button("Cancelar");
    //Instancia a salida
    private Salida salida;

    public Dashboard_Salidas(){
        addClassName("menu-registro");
        //Llamada al binder que muestra los items seleccionados
        binder.bindInstanceFields(this);

        //Campo de registro fecha
        //singleFormatI18n.setDateFormat("yyyy-MM-dd");
        //fechaEntrada.setI18n(singleFormatI18n);
        //Campo de registro hora
        //horaEntrada.setLabel("Hora de entrada");
        //horaEntrada.setValue(LocalTime.of(7, 0));

        //Combo box para identificador del kiosko
        identificadorKiosko.setAllowCustomValue(true);
        identificadorKiosko.setItems("Kiosko 1", "Kiosko 2", "Kiosko 3", "Kiosko 4");

        add(claveEmpleado,fechaSalida,horaSalida,identificadorKiosko, botonesVentana());
    }

    public void setSalida(Salida salida){
        this.salida = salida;
        binder.readBean(salida);
    }

    private Component botonesVentana() {
        registrar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        eliminar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        cerrar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        //Acciones ya programadas de los botones
        registrar.addClickListener(event -> validarGuardar());
        eliminar.addClickListener(event -> fireEvent(new DeleteEvent(this, salida)));
        cerrar.addClickListener(event -> fireEvent(new CloseEvent(this)));

        //Pulsaciones de teclas
        registrar.addClickShortcut(Key.ENTER);
        cerrar.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(registrar, eliminar, cerrar);
    }
    private void validarGuardar() {
        try{
            binder.writeBean(salida);
            fireEvent(new SaveEvent(this, salida));
        }catch(ValidationException e){
            e.printStackTrace();
        }
    }

    //Eventos
    public static abstract class ContactFormEvent extends ComponentEvent<Dashboard_Salidas> {
        private Salida salida;

        protected ContactFormEvent(Dashboard_Salidas source, Salida salida) {
            super(source, false);
            this.salida = salida;
        }

        public Salida getSalida() {
            return salida;
        }
    }

    public static class SaveEvent extends Dashboard_Salidas.ContactFormEvent {
        SaveEvent(Dashboard_Salidas source, Salida salida) {
            super(source, salida);
        }
    }

    public static class DeleteEvent extends Dashboard_Salidas.ContactFormEvent {
        DeleteEvent(Dashboard_Salidas source, Salida salida) {
            super(source, salida);
        }
    }

    public static class CloseEvent extends Dashboard_Salidas.ContactFormEvent {
        CloseEvent(Dashboard_Salidas source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}

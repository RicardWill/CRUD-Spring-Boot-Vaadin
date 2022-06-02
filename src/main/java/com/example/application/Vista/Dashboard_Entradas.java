package com.example.application.Vista;

import com.example.application.Persistencia.Entidad.Entrada;
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
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;


public class Dashboard_Entradas extends FormLayout {
    //Mandar los datos al formulario o menu
    Binder<Entrada> binder = new BeanValidationBinder<>(Entrada.class);
    TextField claveEmpleado = new TextField("Clave de empleado");
    //DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
    //DatePicker fechaEntrada = new DatePicker("Fecha de registro");
    LocalDateTime locaDate = LocalDateTime.now();

    String dia = String.valueOf(locaDate.getDayOfMonth());
    String mes = String.valueOf(locaDate.getMonth());
    String anio = String.valueOf(locaDate.getYear());
    ComboBox<String> fechaEntrada = new ComboBox<>("Fecha entrada",dia.concat("/").concat(mes).concat("/").concat(anio));
    //TimePicker horaEntrada = new TimePicker();


    String segundos = String.valueOf(locaDate.getSecond());
    String minutos = String.valueOf(locaDate.getMinute());
    String hours  = String.valueOf(locaDate.getHour());

    ComboBox<String> horaEntrada = new ComboBox<>("Hora de entrada",hours.concat(":").concat(minutos).concat(":").concat(segundos));
    ComboBox<String> identificadorKiosko = new ComboBox<>("Identificador kiosko");

    //Botones de instruccion
    Button registrar = new Button("Registrar");
    Button eliminar = new Button("Eliminar");
    Button cerrar = new Button("Cancelar");
    //Instancia a entrada
    private Entrada entrada;

    public Dashboard_Entradas(){
        addClassName("menu-registro");

        //Llamada al binder que muestra los items seleccionados
        binder.bindInstanceFields(this);


        horaEntrada.setValue("DARAA");
        horaEntrada.setReadOnly(false);
        //horaEntrada.setClearButtonVisible(true);
        //horaEntrada.setPrefixComponent(VaadinIcon.MAP_MARKER.create());

        //Campo de registro fecha
        //singleFormatI18n.setDateFormat("yyyy-MM-dd");
        //fechaEntrada.setI18n(singleFormatI18n);
        //Campo de registro hora
        //horaEntrada.setLabel("Hora de entrada");
        //horaEntrada.setValue(LocalTime.of(7, 0));

        //Combo box para identificador del kiosko
        identificadorKiosko.setAllowCustomValue(true);
        identificadorKiosko.setItems("Kiosko 1", "Kiosko 2", "Kiosko 3", "Kiosko 4");

        add(claveEmpleado,fechaEntrada,horaEntrada,identificadorKiosko, botonesVentana());
    }

    public void setEntrada(Entrada entrada){
        this.entrada = entrada;
        binder.readBean(entrada);
    }
    private Component botonesVentana() {
        registrar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        eliminar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        cerrar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        //Acciones ya programadas de los botones
        registrar.addClickListener(event -> validarGuardar());
        eliminar.addClickListener(event -> fireEvent(new DeleteEvent(this,entrada)));
        cerrar.addClickListener(event -> fireEvent(new CloseEvent(this)));

        //Pulsaciones de teclas
        registrar.addClickShortcut(Key.ENTER);
        cerrar.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(registrar, eliminar, cerrar);
    }

    private void validarGuardar() {
        try{
            binder.writeBean(entrada);
            fireEvent(new SaveEvent(this, entrada));
        }catch(ValidationException ex){
            //e.printStackTrace();
            System.out.println("Error de validación "+ex);
        }
    }

    //Eventos
    public static abstract class ContactFormEvent extends ComponentEvent<Dashboard_Entradas> {
        private Entrada entrada;

        protected ContactFormEvent(Dashboard_Entradas source, Entrada entrada) {
            super(source, false);
            this.entrada = entrada;
        }

        public Entrada getEntrada() {
            return entrada;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(Dashboard_Entradas source, Entrada entrada) {
            super(source, entrada);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(Dashboard_Entradas source, Entrada entrada) {
            super(source, entrada);
        }
    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(Dashboard_Entradas source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

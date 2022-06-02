package com.example.application.Vista;

import com.example.application.Controlador.ControlEntradas;
import com.example.application.Controlador.ControlSalidas;
import com.example.application.Persistencia.Entidad.Entrada;
import com.example.application.Persistencia.Entidad.Salida;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "salidas", layout = Barra_Navegacion.class)
@PageTitle("Chequeo de salidas")
public class Vista_Salidas extends VerticalLayout {


    Grid<Salida> grid = new Grid<>(Salida.class);

    TextField buscar = new TextField();

    //Instancia al formulario de registro
    Dashboard_Salidas menu;

    //Instancia al controlador con la bd
    ControlSalidas controlSalidas;

    public Vista_Salidas(ControlSalidas controlSalidas) {
        this.controlSalidas = controlSalidas;
        addClassName("vista-entradas");
        setSizeFull();

        //Para la tabla
        configureGrid();
        //Para el formulario o menu de opciones
        configureForm();
        //Para el muestreo de datos

        add(
                barraBusqueda(),
                disenoPagina()
        );

        actualizarTabla();

        cerrarMenu();

    }

    private void configureGrid() {
        grid.addClassName("cuadricula-datos");
        grid.setSizeFull();
        //Mostrar las columnas
        grid.setColumns("claveEmpleado","fechaSalida","horaSalida", "identificadorKiosko");
        //grid.addColumn(entrada -> entrada.getClaveEmpleado().);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        //Simple selector y muestro de dato
        grid.asSingleSelect().addValueChangeListener(event -> editarMenu(event.getValue()));
    }

    private void configureForm() {
        menu = new Dashboard_Salidas();
        menu.setWidth("25em");

        //Opciones para CRUD sobre la BD
        //Crear y modificar
        menu.addListener(Dashboard_Salidas.SaveEvent.class, this::guardarSalida);
        //Eliminar
        menu.addListener(Dashboard_Salidas.DeleteEvent.class, this::eliminarSalida);
        //Cierre de menu
        menu.addListener(Dashboard_Salidas.CloseEvent.class, event -> cerrarMenu());
        //menu.registrar.addClickListener(event -> {eventoBotonAceptar();});
    }

    private Component barraBusqueda() {
        //Campo de busqueda
        buscar.setPlaceholder("Buscar por id empleado");
        //evitar que sobreescriban el boton
        buscar.setClearButtonVisible(true);
        buscar.setValueChangeMode(ValueChangeMode.LAZY);
        buscar.addValueChangeListener(event -> actualizarTabla());

        Button btnRegistrarSalida = new Button("Registrar Salida");
        btnRegistrarSalida.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        HorizontalLayout barraHerramientas = new HorizontalLayout(buscar, btnRegistrarSalida);
        //Funcionalidad de registro de entrada
        btnRegistrarSalida.addClickListener(event -> agregarSalida());

        btnRegistrarSalida.setAutofocus(true);
        buscar.setAutofocus(true);

        barraHerramientas.addClassName("barra-herramientas");
        return barraHerramientas;
    }

    private Component disenoPagina() {
        HorizontalLayout pagina = new HorizontalLayout(grid, menu);

        pagina.addClassName("contenido-pagina");
        //Orientacion de los componentes de la pagina
        pagina.setFlexGrow(2, grid);
        pagina.setFlexGrow(1, menu);
        pagina.setSizeFull();

        return pagina;
    }

    private void actualizarTabla() {
        grid.setItems(controlSalidas.findAllSalidas(buscar.getValue()));

    }

    private void cerrarMenu() {
        menu.setSalida(null);
        menu.setVisible(false);
        removeClassName("menu-data");
    }

    private void editarMenu(Salida salida) {
        if(salida == null){
            cerrarMenu();
        }else{
            menu.setSalida(salida);
            menu.setVisible(true);
            addClassName("menu-data");
        }
    }

    private void agregarSalida() {
        grid.asSingleSelect().clear();
        editarMenu(new Salida());
    }

    //Acciones del menu sobre la bd
    private void guardarSalida(Dashboard_Salidas.SaveEvent event){
        controlSalidas.saveSalida(event.getSalida());
        actualizarTabla();
        cerrarMenu();
    }

    private void eliminarSalida(Dashboard_Salidas.DeleteEvent event){
        controlSalidas.deleteSalida(event.getSalida());
        actualizarTabla();
        cerrarMenu();
    }
}

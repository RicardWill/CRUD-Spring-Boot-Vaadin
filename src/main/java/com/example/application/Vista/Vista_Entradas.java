package com.example.application.Vista;

import com.example.application.Controlador.ControlEntradas;
import com.example.application.Persistencia.Entidad.Entrada;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "", layout = Barra_Navegacion.class)
@PageTitle("Chequeo de entradas")
public class Vista_Entradas extends VerticalLayout {

    Grid<Entrada> grid = new Grid<>(Entrada.class);

    TextField buscar = new TextField();

    //Instancia al formulario de registro
    Dashboard_Entradas menu;

    //Instancia al controlador con la bd
    ControlEntradas controlEntradas;

    public Vista_Entradas(ControlEntradas controlEntradas) {
        this.controlEntradas = controlEntradas;
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



    private Component barraBusqueda() {
        //Campo de busqueda
        buscar.setPlaceholder("Buscar por id empleado");
        //evitar que sobreescriban el boton
        buscar.setClearButtonVisible(true);
        buscar.setValueChangeMode(ValueChangeMode.LAZY);
        buscar.addValueChangeListener(event -> actualizarTabla());

        Button btnRegistrarEntrada = new Button("Registrar Entrada");
        btnRegistrarEntrada.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        HorizontalLayout barraHerramientas = new HorizontalLayout(buscar, btnRegistrarEntrada);
        //Funcionalidad de registro de entrada
        btnRegistrarEntrada.addClickListener(event -> agregarEntrada());

        btnRegistrarEntrada.setAutofocus(true);
        buscar.setAutofocus(true);

        barraHerramientas.addClassName("barra-herramientas");
        return barraHerramientas;
    }


    private void configureGrid() {
        grid.addClassName("cuadricula-datos");
        grid.setSizeFull();
        //Mostrar las columnas
        grid.setColumns("claveEmpleado","fechaEntrada","horaEntrada", "identificadorKiosko");
        //grid.addColumn(entrada -> entrada.getClaveEmpleado().);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        //Simple selector y muestro de dato
        grid.asSingleSelect().addValueChangeListener(event -> editarMenu(event.getValue()));
    }



    private void configureForm() {
        menu = new Dashboard_Entradas();
        menu.setWidth("25em");

        //Opciones para CRUD sobre la BD
        //Crear y modificar
        menu.addListener(Dashboard_Entradas.SaveEvent.class, this::guardarEntrada);
        //Eliminar
        menu.addListener(Dashboard_Entradas.DeleteEvent.class, this::eliminarEntrada);
        //Cierre de menu
        menu.addListener(Dashboard_Entradas.CloseEvent.class, event -> cerrarMenu());
        //menu.registrar.addClickListener(event -> {eventoBotonAceptar();});
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
        grid.setItems(controlEntradas.findAllEntradas(buscar.getValue()));

    }

    private void cerrarMenu() {
        menu.setEntrada(null);
        menu.setVisible(false);
        removeClassName("menu-data");
    }

    private void editarMenu(Entrada entrada) {
        if(entrada == null){
            cerrarMenu();
        }else{
            menu.setEntrada(entrada);
            menu.setVisible(true);
            addClassName("menu-data");
        }
    }

    private void agregarEntrada() {
        grid.asSingleSelect().clear();
        editarMenu(new Entrada());
    }

    //Acciones del menu sobre la bd
    private void guardarEntrada(Dashboard_Entradas.SaveEvent event){
        controlEntradas.saveEntrada(event.getEntrada());
        actualizarTabla();
        cerrarMenu();
    }

    private void eliminarEntrada(Dashboard_Entradas.DeleteEvent event){
        controlEntradas.deleteEntrada(event.getEntrada());
        actualizarTabla();
        cerrarMenu();
    }
}

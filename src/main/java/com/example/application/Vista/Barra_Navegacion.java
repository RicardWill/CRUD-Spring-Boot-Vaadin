package com.example.application.Vista;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLink;


public class Barra_Navegacion extends AppLayout{

    public Barra_Navegacion() {
        addToNavbar(headerPagina());
    }

    public static class MenuItemInfo extends ListItem {
        private final Class<? extends Component> view;

        public Class<?> getView() {
            return view;
        }


        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames("flex", "h-m", "items-center", "px-s", "relative", "text-secondary");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames("font-medium", "text-s", "whitespace-nowrap");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                // Use Lumo classnames for suitable font size and margin
                addClassNames("me-s", "text-l");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }



    private Component headerPagina() {
        Header header = new Header();
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "flex-col", "w-full");

        Div layout = new Div();
        layout.addClassNames("flex", "h-xl", "items-center", "px-l");




        H1 appName = new H1("GESTIÓN DE ENTRADAS & SALIDAS");

        appName.addClassNames("my-0", "me-auto", "text-l");
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames("flex", "gap-s", "overflow-auto", "px-m");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("flex", "list-none", "m-0", "p-0");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }

        header.add(layout, nav);
        return header;
    }


    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Checar Entradas", "las la-door-closed", Vista_Entradas.class),
                new MenuItemInfo("Checar Salidas", "las la-door-open", Vista_Salidas.class),

        };
    }
}
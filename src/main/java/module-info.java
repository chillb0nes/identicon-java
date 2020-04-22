module identicon {
    requires java.desktop;
    requires args4j;

    exports org.example;

    opens org.example to args4j;
    opens org.example.util to args4j;
}
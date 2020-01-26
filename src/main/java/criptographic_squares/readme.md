# Criptographic Squares

## Package structure

This package contains the __runnable application__ itself:

#### `CriptoSquareApplication.java`

Extends __PApplet__, which is the main class of the [Processing library](https://processing.org).

Contains two different layouts of the model objects on the screen (_Impl 1_ and _Impl 2_).

There are also some ugly comments, some refactoring will be made to avoid this in future projects.

---

This package also has __2 sub-pakages:__

### model

Contains the POJOs (Plain Old Java Objects) responsible for holding and maintaining their state. This encapsulated state
is used through getter methods to then draw the objects.

#### `Stick.java`

A rectangle that can be vissible or hidden.

#### `StickFigure.java`

A specific figure layout composed of Stick objects. Receives the state as a binary String of lenghth 12 `"000111000111"`
where each digit represents the visibility of each stick it has and updates its Sticks positions Accordingly. This
object will be displayed in it's position and is resizable through the private field `innerSquareSize`.

### view

Contains the classes responsible for __displaying the model objects__ using their encapsulated state.

These objects __receive a PApplet instance__ in the constructor (which is the application itself, oddly enough)in order
to be able to invoke through it the non-static methods of the Processing library that draw to the output screen.

#### `StickDrawer.java`

Draws Stick objects.

#### `StickFigureDrawer.java`

Draws StickFigure objects.

## Building the application

This application is compiled to Java 8 and uses [Maven](https://maven.apache.org/) to resolve its dependencies.

However, if you don't want to setup maven you can download all the .JAR dependencies specified in the base project's
`pom.xml` and add them to your classpath and it should work just fine.

## Running the application

Run the main method in `CriptoSquareApplication.java`.

## Running the tests

Go to `\src\test\java\criptographic_squares\` and select _Run 'All Tests'_ in your IDE (or however else you run your
tests with).

You do write tests right? lol.

## Finding the resources

Resources can be found in `\src\main\resources\criptographic_squares`

Renders and frame exports are stored here.

## Final result

[View on Facebook](https://www.facebook.com/leveneden.art/posts/155947875849483)

View on Instagram (soon)




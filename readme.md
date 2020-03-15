# Progessing 2020

To do:
- [ ] Refactor `Drawable` interface
  - [X] Add method void `draw()` to the interface
  - [X] Make all model classes to implement `Drawable` with their own type
  - [ ] Make all drawers draw only their respective models
  - [ ] Remove casting in classes that implement `Drawable`
  - [ ] Make all models call the draw method with a delegation to their respective drawers
  - [ ] Replace all calls to `Drawer.draw()` with `Model.draw()`
- [ ] Implement Drawer Strategy
- [ ] Implement Layout strategy
# gilded-rose-refactoring

[![GildedRoseRefactoring](https://github.com/SarthakMakhija/gilded-rose-refactoring/actions/workflows/build.yml/badge.svg)](https://github.com/SarthakMakhija/gilded-rose-refactoring/actions/workflows/build.yml) [![codecov](https://codecov.io/github/SarthakMakhija/gilded-rose-refactoring/graph/badge.svg?token=M1Y3TZZDT7)](https://codecov.io/github/SarthakMakhija/gilded-rose-refactoring)

### Idea

This repository aims to refactor [GildedRose Kata](https://kata-log.rocks/gilded-rose-kata) for teaching refactoring.
The refactored code addresses numerous code smells present in the original implementation, including:

- [Inappropriate Intimacy](https://refactoring.guru/smells/inappropriate-intimacy)
- [Feature envy](https://refactoring.guru/smells/feature-envy)
- [Long method](https://refactoring.guru/smells/long-method)
- [Duplicate code](https://refactoring.guru/smells/duplicate-code)
- [Data class](https://refactoring.guru/smells/data-class)
- [Switch statements](https://refactoring.guru/smells/switch-statements)

The code to refactor is available [here](https://github.com/SarthakMakhija/gilded-rose-refactoring/tree/original).

### Refactoring techniques used

The following refactoring techniques were used:

- [Move method](https://refactoring.guru/move-method)
- [Replace temp with query](https://refactoring.guru/replace-temp-with-query)
- [Rename method](https://refactoring.guru/rename-method)
- [Extract method](https://refactoring.guru/extract-method)
- [Replace constructor with factory method](https://refactoring.guru/replace-constructor-with-factory-method)
- [Replace conditional with polymorphism](https://refactoring.guru/replace-conditional-with-polymorphism)
--> _Conditionals were replaced with appropriate abstractions containing [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)._

### What is not covered

- This repository does not implement any feature mentioned in the [GildedRose Kata](https://kata-log.rocks/gilded-rose-kata).
- This repository does not implement solitary unit-tests. All the unit-tests are sociable.
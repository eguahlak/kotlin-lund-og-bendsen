open class Pet(val name: String, var age: Int)

class Dog(val barkPitch: String, name: String, age: Int) : Pet(name, age)

class Cat(var lifeCount: Int = 9, name: String, age: Int) : Pet(name, age)

fun describe(pet: Pet) =
  when (pet) {
    is Dog -> "${pet.name} is a dog that barks in ${pet.barkPitch}"
    is Cat -> "Only ${pet.lifeCount} left for ${pet.name}"
    else -> "${pet.name} is probably a ${pet.age} years old rabbit"
    }

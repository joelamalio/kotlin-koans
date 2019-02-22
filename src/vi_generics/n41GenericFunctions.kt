package vi_generics

import util.TODO
import java.util.*
import java.util.function.Predicate

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
    references = { l: List<Int> ->
        l.partition { it > 0 }
        l.toCollection(HashSet<Int>())
    }
)

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return this.partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}
/*
private fun <E> List<E>.partitionTo(c1: List<E>, c2: List<E>, predicate: (E) -> Boolean): Pair<List<E>, List<E>> {
    return this.partition(predicate)
}

private fun <E> Set<E>.partitionTo(c1: Set<E>, c2: Set<E>, predicate: (E) -> Boolean): Pair<Set<E>, Set<E>> {
    val pair = this.partition(predicate)
    return Pair(pair.first.toSet(), pair.second.toSet())
}
*/
private fun <E, C: MutableCollection<E>> Collection<E>.partitionTo(first: C, second: C, predicate: (E) -> Boolean): Pair<C, C> {
    for (element in this) {
        if (predicate(element)) {
            first.add(element)
        } else {
            second.add(element)
        }
    }
    return Pair(first, second)
}
package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year == other.year) {
            if (month == other.month) {
                return dayOfMonth.compareTo(other.dayOfMonth)
            }
            return month.compareTo(other.month)
        }

        return year.compareTo(other.year)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    private var actual: MyDate = start

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            override fun hasNext(): Boolean {
                return actual <= endInclusive
            }

            override fun next(): MyDate {
                val next = actual
                actual = actual.nextDay()
                return next
            }

        }
    }


    operator fun contains(d: MyDate): Boolean {
        return d >= start && d <= endInclusive
    }
}

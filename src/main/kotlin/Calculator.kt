/*
 * MIT License
 *
 * Copyright (c) 2023 Jorge Garcia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.time.LocalDate
import java.time.Month.MAY
import kotlin.math.pow

data class Person(
    val firstName: String,
    val lastName: String,
    val dob: LocalDate,
    val email: String
)

open class Machine {

    init {
        start()
    }

    private fun start() {
        println("Running the machine!")
    }
}

enum class Operation(val symbol: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/")
}

data class OperationRequest<E : Number>(
    val operand1: E,
    val operand2: E,
    val operation: Operation? = null
) {
    override fun toString(): String {
        return if (operation == null) "an operation with $operand1 and $operand2"
        else "$operand1 ${operation.symbol} $operand2"
    }
}

inline fun <reified E : Number> pow(base: E, exponent: E): E {
    return when (E::class) {
        Float::class -> base.toFloat().pow(exponent.toFloat()) as E
        Double::class -> base.toDouble().pow(exponent.toDouble()) as E
        Long::class -> base.toLong().toDouble().pow(exponent.toLong().toDouble()).toLong() as E
        Int::class -> base.toInt().toDouble().pow(exponent.toInt().toDouble()).toInt() as E
        Short::class -> base.toShort().toDouble().pow(exponent.toShort().toDouble()).toInt().toShort() as E
        Byte::class -> base.toByte().toDouble().pow(exponent.toByte().toDouble()).toInt().toByte() as E
        else -> throw IllegalArgumentException("Not supported type")
    }
}

inline fun <reified E : Number> module(dividend: E, divisor: E): E {
    return when (E::class) {
        Float::class -> dividend.toFloat().rem(divisor.toFloat()) as E
        Double::class -> dividend.toDouble().rem(divisor.toDouble()) as E
        Long::class -> dividend.toLong().rem(divisor.toLong()) as E
        Int::class -> dividend.toInt().rem(divisor.toInt()) as E
        Short::class -> dividend.toShort().rem(divisor.toShort()) as E
        Byte::class -> dividend.toByte().rem(divisor.toByte()) as E
        else -> throw IllegalArgumentException("Not supported type")
    }
}

class Calculator<E : Number>(
    private val person: Person,
    val operationRequest: OperationRequest<E>,
    private val externalOperation: ((E, E) -> E)? = null
) : Machine() {

    init {
        greet()
    }

    private fun greet() {
        println("Hello ${person.firstName} ${person.lastName}")
        println("You requested $operationRequest")
    }

    fun calculate(): E {
        if (operationRequest.operation == null) {
            return externalOperation?.invoke(operationRequest.operand1, operationRequest.operand2) as E
        }

        return when (operationRequest.operation) {
            Operation.ADD -> add()
            Operation.SUBTRACT -> subtract()
            Operation.MULTIPLY -> multiply()
            Operation.DIVIDE -> divide()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun add(): E {
        return when (operationRequest.operand1::class) {
            Float::class -> operationRequest.operand1.toFloat() + operationRequest.operand2.toFloat()
            Double::class -> operationRequest.operand1.toDouble() + operationRequest.operand2.toDouble()
            Long::class -> operationRequest.operand1.toLong() + operationRequest.operand2.toLong()
            Int::class -> operationRequest.operand1.toInt() + operationRequest.operand2.toInt()
            Short::class -> operationRequest.operand1.toShort() + operationRequest.operand2.toShort()
            Byte::class -> operationRequest.operand1.toByte() + operationRequest.operand2.toByte()
            else -> throw IllegalArgumentException("Not supported type")
        } as E
    }

    @Suppress("UNCHECKED_CAST")
    private fun subtract(): E {
        return when (operationRequest.operand1::class) {
            Float::class -> operationRequest.operand1.toFloat() - operationRequest.operand2.toFloat()
            Double::class -> operationRequest.operand1.toDouble() - operationRequest.operand2.toDouble()
            Long::class -> operationRequest.operand1.toLong() - operationRequest.operand2.toLong()
            Int::class -> operationRequest.operand1.toInt() - operationRequest.operand2.toInt()
            Short::class -> operationRequest.operand1.toShort() - operationRequest.operand2.toShort()
            Byte::class -> operationRequest.operand1.toByte() - operationRequest.operand2.toByte()
            else -> throw IllegalArgumentException("Not supported type")
        } as E
    }

    @Suppress("UNCHECKED_CAST")
    private fun multiply(): E {
        return when (operationRequest.operand1::class) {
            Float::class -> operationRequest.operand1.toFloat() * operationRequest.operand2.toFloat()
            Double::class -> operationRequest.operand1.toDouble() * operationRequest.operand2.toDouble()
            Long::class -> operationRequest.operand1.toLong() * operationRequest.operand2.toLong()
            Int::class -> operationRequest.operand1.toInt() * operationRequest.operand2.toInt()
            Short::class -> operationRequest.operand1.toShort() * operationRequest.operand2.toShort()
            Byte::class -> operationRequest.operand1.toByte() * operationRequest.operand2.toByte()
            else -> throw IllegalArgumentException("Not supported type")
        } as E
    }

    @Suppress("UNCHECKED_CAST")
    private fun divide(): E {
        return when (operationRequest.operand1::class) {
            Float::class -> operationRequest.operand1.toFloat() / operationRequest.operand2.toFloat()
            Double::class -> operationRequest.operand1.toDouble() / operationRequest.operand2.toDouble()
            Long::class -> operationRequest.operand1.toLong() / operationRequest.operand2.toLong()
            Int::class -> operationRequest.operand1.toInt() / operationRequest.operand2.toInt()
            Short::class -> operationRequest.operand1.toShort() / operationRequest.operand2.toShort()
            Byte::class -> operationRequest.operand1.toByte() / operationRequest.operand2.toByte()
            else -> throw IllegalArgumentException("Not supported type")
        } as E
    }
}

fun <E : Number> Calculator<E>.operandsToBinaryString(): String = when (operationRequest.operand1::class) {
    Float::class -> "${operationRequest.operand1.toFloat().toBits().toString(2)} ${
        operationRequest.operand2.toFloat().toBits().toString(2)
    }"

    Double::class -> "${
        operationRequest.operand1.toDouble().toBits().toString(2)
    } ${operationRequest.operand2.toDouble().toBits().toString(2)}"

    Long::class -> "${operationRequest.operand1.toLong().toString(2)} ${
        operationRequest.operand2.toLong().toString(2)
    }"

    Int::class -> "${operationRequest.operand1.toInt().toString(2)} ${
        operationRequest.operand2.toInt().toString(2)
    }"

    Short::class -> "${operationRequest.operand1.toShort().toString(2)} ${
        operationRequest.operand2.toShort().toString(2)
    }"

    Byte::class -> "${operationRequest.operand1.toByte().toString(2)} ${
        operationRequest.operand2.toByte().toString(2)
    }"

    else -> throw IllegalArgumentException("Not supported type")
}

fun startCalculator() {
    val person = Person(
        "Jorge", "Garcia", LocalDate.of(1982, MAY, 12),
        "mr-george@georgethepenguin.dev"
    )

    val addOperationRequest = OperationRequest(1.9, 2.8, Operation.ADD)
    val addCalculator = Calculator(person, addOperationRequest)
    val addResult = addCalculator.calculate()
    println("Add Result: $addResult")

    val powOperationRequest = OperationRequest(3, 4)
    val powCalculator = Calculator(person, powOperationRequest, ::pow)
    val powResult = powCalculator.calculate()
    println("Pow Result: $powResult")

    val moduleOperationRequest = OperationRequest(50, 6)
    val moduleCalculator = Calculator(person, moduleOperationRequest, ::module)
    val moduleResult = moduleCalculator.calculate()
    println("Module Result: $moduleResult")
    println("Operand in Binary: ${moduleCalculator.operandsToBinaryString()}")
}

fun main() {
    startCalculator()
}
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

data class Person(val firstName: String, val lastName: String, val dob: LocalDate, val email: String)

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

data class OperationRequest<E : Number>(val operand1: E, val operand2: E, val operation: Operation) {
    override fun toString(): String {
        return "$operand1 ${operation.symbol} $operand2"
    }
}

class Calculator<E : Number>(val person: Person, val operationRequest: OperationRequest<E>) : Machine() {

    init {
        greet()
    }

    private fun greet() {
        println("Hello ${person.firstName} ${person.lastName}")
        println("You requested $operationRequest")
    }

    fun calculate(): E {
        return when (operationRequest.operation) {
            Operation.ADD -> add()
            Operation.SUBTRACT -> subtract()
            Operation.MULTIPLY -> multiply()
            Operation.DIVIDE -> divide()
        }
    }

    private fun divide(): E {
        return (operationRequest.operand1.toFloat() / operationRequest.operand2.toFloat()) as E
    }

    private fun multiply(): E {
        return (operationRequest.operand1.toFloat() * operationRequest.operand2.toFloat()) as E
    }

    private fun subtract(): E {
        return (operationRequest.operand1.toFloat() - operationRequest.operand2.toFloat()) as E
    }

    private fun add(): E {
        return (operationRequest.operand1.toFloat() + operationRequest.operand2.toFloat()) as E
    }
}

fun main(args: Array<String>) {
    val person = Person("Jorge", "Garcia", LocalDate.of(1982, MAY, 12),
        "mr-george@georgethepenguin.dev")

    val operationRequest = OperationRequest(1.0, 2.0, Operation.ADD)

    val calculator = Calculator(person, operationRequest)
    val result = calculator.calculate()
    print("Result: $result")
}
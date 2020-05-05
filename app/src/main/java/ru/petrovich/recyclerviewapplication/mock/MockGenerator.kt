package ru.petrovich.recyclerviewapplication.mock

import java.util.*
import kotlin.collections.ArrayList

class MockGenerator {
    companion object Generator {
        fun generate(count: Int): List<Mock> {
            val mocks = ArrayList<Mock>(count)
            for (i in 0..count) {
                mocks.add(Mock(UUID.randomUUID().toString(), Random().nextInt(200)))
            }
            return mocks
        }
    }
}
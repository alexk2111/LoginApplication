package com.onix.internship.okucherenko.domain.usecase

import android.content.Context
import android.util.Log
import com.onix.internship.okucherenko.domain.entity.Dictionary
import com.onix.internship.okucherenko.domain.entity.DictionaryItem
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class XmlDictionaryParser(val context: Context) {

    val dict = "dict.xdxf"
    fun parse() {
        try {
            val parserFactory = XmlPullParserFactory.newInstance()
            val parser = parserFactory.newPullParser()
            val inputStream = context.resources.assets.open("dict.xdxf")
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)

            var tag: String? = ""
            var text: String? = ""
            var event = parser.eventType

            while (event != XmlPullParser.END_DOCUMENT) {
                tag = parser.name
                when (parser.eventType) {
                    XmlPullParser.START_TAG -> {
                        when (tag) {
                            "xdxf" -> {
                                Dictionary.langFrom = parser.getAttributeValue(0)
                                Dictionary.langTo = parser.getAttributeValue(1)
                                Dictionary.format = parser.getAttributeValue(2)
                            }
                        }
                    }
                    XmlPullParser.TEXT -> {
                        text = parser.text
                    }
                    XmlPullParser.END_TAG -> {
                        when (tag) {
                            "full_name" -> Dictionary.fullName = text.toString()
                            "description" -> Dictionary.description = text.toString()
                            "ar" -> {
                                val itog = text?.replace("\n", "")?.replace("\"", "")?.split("  ")
                                Dictionary.dictionary.add(
                                    (DictionaryItem(
                                        itog?.get(0) ?: "",
                                        itog?.get(1) ?: ""
                                    ))
                                )
                            }
                        }
                    }
                }
                event = parser.next()
            }
        } catch (t: Throwable) {
            Log.e("Error ", "No load dictionary")
        }
    }
}
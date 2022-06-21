package com.onix.internship.okucherenko.arch.adapter

interface AdapterContentElement {

    fun areContentsTheSame(other: AdapterContentElement): Boolean
}
package com.miladheydari.interview.s780androidtest.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.miladheydari.interview.s780androidtest.db.entity.Commodity
import java.util.*

object Constants {

    object NetworkService {
        const val BASE_URL = "https://httpbin.org/"

        const val RATE_LIMITER_TYPE = "data"


        const val SERVER_RESPONSE_STRING = """[
	{
		"id":0,
		"title":"شلوار مردانه",
		"categoryColor":"#f57f17"
	},
	{
		"id":1,
		"title":"کت تک",
		"categoryColor":"#f57f17"
	},
	{
		"id":2,
		"title":"پیراهن مردانه",
		"categoryColor":"#f57f17"
	},
	{
		"id":3,
		"title":"پیراهن زنانه",
		"categoryColor":"#f57f17"
	},
	{
		"id":4,
		"title":"شلوار زنانه",
		"categoryColor":"#f57f17"
	},
	{
		"id":5,
		"title":"توپ فوتبال",
		"categoryColor":"#6abf69"
	},
	{
		"id":6,
		"title":"میز بیلیارد",
		"categoryColor":"#6abf69"
	},
	{
		"id":7,
		"title":"توپ والیبال",
		"categoryColor":"#6abf69"
	},
	{
		"id":8,
		"title":"چاقوی آشپزخانه",
		"categoryColor":"#fdd835"
	},
	{
		"id":9,
		"title":"قابلمه 6 تکه",
		"categoryColor":"#fdd835"
	},
	{
		"id":10,
		"title":"گیتار",
		"categoryColor":"#1976d2"
	},
	{
		"id":11,
		"title":"پیانو",
		"categoryColor":"#1976d2"
	},
	{
		"id":12,
		"title":"گیتار برقی",
		"categoryColor":"#1976d2"
	}
]
"""
    }

}

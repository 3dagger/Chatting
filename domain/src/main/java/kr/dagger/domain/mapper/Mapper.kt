package kr.dagger.domain.mapper

interface Mapper<in FromObject, ToObject> {

	fun convert(fromObject: FromObject): ToObject

}
package com.pancisin.webappcore.utils

import java.sql.SQLException
import org.hibernate.Session
import org.hibernate.tuple.ValueGenerator

import com.github.slugify.Slugify
import com.pancisin.webappcore.domain.annotations.NameIdentifier
import javax.persistence.Table
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class UniqueSlugGenerator : ValueGenerator<String> {

  override fun generateValue(session: Session, domain: Any): String? {
    val ann = domain::class.findAnnotation<Table>()

    if (ann != null) {
      domain::class.memberProperties.find { it.findAnnotation<NameIdentifier>() != null }?.let {
        val name = it.getter.call(domain) as String
        var slug = Slugify().slugify(name)

        session.doWork { connection ->
          try {
            val query = "select count(id) as slug_count from ${ann.name} WHERE slug LIKE '$slug%'"
            val rs = connection.createStatement().executeQuery(query)

            if (rs.next()) {
              val number = rs.getInt(1)

              if (number > 0) {
                slug = arrayOf(slug, rs.getInt(1).toString()).joinToString("-")
              }
            }
          } catch (ex: SQLException) {
            ex.printStackTrace()
          }
        }

        return slug
      }
    }

    return null;
  }
}

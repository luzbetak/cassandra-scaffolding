package com.kduraj

import com.datastax.driver.core.{ResultSet, Session}
import com.kduraj.utils.ConfigReader

import scala.collection.JavaConversions

case class AccountProfile(id: Int, name: String, max_rating: Int)

trait QueryHandler {

  /**
    * CRUD operations
    * @param session
    */
  def runQuery(session: Session): Unit = {

    val keyspace = ConfigReader.getKeyspaceName
    val tableName = ConfigReader.getTableName

    val selectQuery = s"SELECT * FROM $tableName"
    val insertQuery = s"INSERT INTO $keyspace.$tableName(id, name, max_rating) values( 2, 'Martin Odersky', 99)"
    val updateQuery = s"UPDATE $keyspace.$tableName set max_rating=100 WHERE id=2"
    val deleteQuery = s"DELETE FROM $keyspace.$tableName WHERE id=2"

    println("\n\nRecords in table Initially...")
    println(displayResult(selectQuery, session))

    // Inserting into table
    println("\n\nInserting one Record ...")
    CassandraConnector.executeQuery(session, insertQuery)
    println(displayResult(selectQuery, session))

    println("\n\nUpdating Inserted Record ...")
    CassandraConnector.executeQuery(session, updateQuery)
    println(displayResult(selectQuery, session))

    //println("\n\nDeleting Inserted Record ...")
    //CassandraConnector.executeQuery(session, deleteQuery)
    //println(displayResult(selectQuery, session))

  }

  /**
    * This method is responsible to fetch the list of records present in the table
    *
    * @param selectQuery :  This is the query to fetch records from cassandra table
    * @param session : It is the cassandra session
    * @return
    */
  def displayResult(selectQuery: String, session: Session): List[AccountProfile] = {

    val resultSet: ResultSet = CassandraConnector.executeQuery(session, selectQuery)
    val iterator = JavaConversions.asScalaIterator(resultSet.iterator)
    val records = iterator map { row =>
      AccountProfile(row.getInt("id"), row.getString("name"), row.getInt("max_rating"))
//      println(s"[ id: ${row.getInt("id")}, name : ${row.getString("name")}, max_rating : ${row.getInt("max_rating")} ]")
    }
    records.toList
  }

}

object QueryHandler extends QueryHandler

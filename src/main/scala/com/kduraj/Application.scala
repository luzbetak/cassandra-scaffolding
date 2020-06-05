package com.kduraj

import com.datastax.driver.core.{Cluster, ResultSet, Session}
import com.kduraj.utils.ConfigReader

import scala.collection.JavaConversions
import scala.util.{Failure, Success, Try}


object Application extends App {

  Try {
    CassandraConnector.getCasssandraBuilder

  } match {

    case Success(cluster) =>
      val session = getCassandraSession(ConfigReader.getKeyspaceName, cluster)
      QueryHandler.runQuery(session)
      session.close()
      cluster.close()

    case Failure(exception) => println("Unable to Connect to Cassandra" + exception)
  }

  private def getCassandraSession(keyspace: String, cluster: Cluster): Session = {

    Try{
      CassandraConnector.getSession(keyspace, cluster)
    } match {
      case Success(session) => session
      case Failure(exception) => throw new Exception("Unable to connect to keyspace" + exception)
    }
  }

}

package crud
import org.mongodb.scala._
import crud.Helpers._

import org.mongodb.scala.{Completed, Document, MongoClient, MongoCollection, MongoDatabase, Observable, Observer}

object MongoScala extends App {

  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("mydb")
  val collection: MongoCollection[Document] = database.getCollection("test");

  val doc: Document = Document("_id" -> 0, "name" -> "Leeroy", "type" -> "database",
    "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))


  val observable: Observable[Completed] = collection.insertOne(doc)
  observable.subscribe(new Observer[Completed] {

    override def onNext(result: Completed): Unit = println("Inserted")

    override def onError(e: Throwable): Unit = println("Failed")

    override def onComplete(): Unit = println("Completed")
  })

  val documents = (1 to 100) map { i: Int => Document("i" -> i) }
  val insertObservable = collection.insertMany(documents)


  val insertAndCount = for {
    insertResult <- insertObservable
    countResult <- collection.countDocuments()
  } yield countResult


  collection.find().first().printHeadResult()

  collection.find().printResults()

  // insertAndCount.head() onComplete {
  //   case Success(value) => println(value)
  //    case Failure(exception) => println(x = "error" + exception.getMessage)
  // }


  //  collection.find().head() onComplete {
  //   case Success(value) => println(value)
  //  case Failure(exception) => println(x = "error" + exception.getMessage)
  //}
  Thread.sleep(5000)



}
//this should go in a different package called service

//save this file as BasicOperation
//package service

//import reactivemongo.bson.BSONDocument

//trait BasicOperations {

//  def removeDocument(document: BSONDocument, collectionName: String, dbName: String): Unit

//  def removeDocuments(document: Seq[BSONDocument], collectionName: String, dbName: String): Unit

//  def insertDocument(document: BSONDocument, collectionName: String, dbName: String): Unit

//  def insertDocuments(document: Seq[BSONDocument], collectionName: String, dbName: String): Unit

// }
// dbconnection file

//package service
//
//import helpers.Constants
//import reactivemongo.api.{DefaultDB, MongoDriver}
//import reactivemongo.api.collections.bson.BSONCollection
//
//import scala.concurrent.duration._
//import scala.concurrent.Await
//import scala.concurrent.ExecutionContext.Implicits.global
//
//trait DBConnection {
//
//  private val driver = new MongoDriver
//  private val connection = driver.connection(List(Constants.localHost.toString))
//
//  def getDB(dbName: String): DefaultDB = {
//    Await.result(connection.database(dbName),10.seconds)
//  }
//
//  def getCollection(collectionName: String, dbName: String): BSONCollection =
//    getDB(dbName).collection[BSONCollection](collectionName)
//
//  def closeConnection(): Unit = driver.close()
//
//}
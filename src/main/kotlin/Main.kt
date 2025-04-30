import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.Scanner

object Notes : Table() {
    val id = integer("id").autoIncrement()
    val text = varchar("text", 255)
    override val primaryKey = PrimaryKey(id)
}

fun main() {
    val dbUrl = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/notesdb"
    val dbUser = System.getenv("DB_USER") ?: "user"
    val dbPassword = System.getenv("DB_PASSWORD") ?: "password"

    Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPassword)

    transaction {
        create(Notes)
    }

    val scanner = Scanner(System.`in`)
    while (true) {
        println("\n1. Add Note\n2. View Notes\n3. Delete Note\n0. Exit")
        when (scanner.nextLine()) {
            "1" -> {
                print("Enter note text: ")
                val noteText = scanner.nextLine()
                transaction {
                    Notes.insert {
                        it[text] = noteText
                    }
                }
                println("Note added!")
            }
            "2" -> {
                transaction {
                    println("Notes:")
                    for (note in Notes.selectAll()) {
                        println("${note[Notes.id]}: ${note[Notes.text]}")
                    }
                }
            }
            "3" -> {
                print("Enter note ID to delete: ")
                val id = scanner.nextLine().toIntOrNull()
                if (id != null) {
                    transaction {
                        Notes.deleteWhere { Notes.id eq id }
                    }
                    println("Note deleted!")
                } else println("Invalid ID")
            }
            "0" -> break
            else -> println("Invalid option")
        }
    }
}

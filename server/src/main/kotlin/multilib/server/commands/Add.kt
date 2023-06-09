package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import organization.OrganizationType
import tools.CreateOrganization
import multilib.utilities.input.*
import multilib.utilities.result.Result

class Add : AbstractCommand, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val creator: CreateOrganization by inject()
    private val type: String
    private val description: String = "add a new element to the collection"
    private var fields: Map<String, Map<String, String>>

    constructor() {
        val typeStr = StringBuilder()
        typeStr.append( "Select your organization type from these options\n" )
        val organizationType = OrganizationType.values()
        for ( i in organizationType.indices ) {
            typeStr.append( organizationType[i].toString() + "\n" )
        }
        type = typeStr.toString()

        fields = mapOf(
            "name" to mapOf<String, String>(
                "title" to "Enter the name of your organization\n",
                "type" to "String"
            ),
            "annualTurnover" to  mapOf<String, String>(
                "title" to "Enter the annual turnover of your organization\n",
                "type" to "Double",
                "min" to "1"
            ),
            "employeesCount" to mapOf<String, String>(
                "title" to "Enter the number of employees in your organization\n",
                "type" to "Int",
                "min" to "1"
            ),
            "x" to mapOf<String, String>(
                "title" to "Enter your organization's X coordinates\n",
                "type" to "Int",
                "min" to "-312"
            ),
            "y" to mapOf<String, String>(
                "title" to "Enter your organization's Y coordinates\n",
                "type" to "Long",
                "max" to "212"
            ),
            "type" to mapOf<String, String>(
                "title" to type,
                "type" to "OrganizationType"
            ),
            "street" to mapOf<String, String>(
                "title" to "Enter the name of the street where your organization is located\n",
                "type" to "String"
            ),
            "zipCode" to mapOf<String, String>(
                "title" to "Enter the street code where your organization is located\n",
                "type" to "String",
                "maxLength" to "27"
            )
        )
    }
    override fun action(data: Map<String, String?>, result: Result): Result {
        val orgComp = OrganizationComparator()
        val org: Organization = creator.create(data, null)

        orgs.add(org)
        orgs.sortWith(orgComp)

        result.setMessage("Done\n")
        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
    override fun commandBuilding(mapData: MutableMap<String, String>, data: String): MutableMap<String, String> {
        val input = InputFile(data)

        for (key in fields.keys) {
            mapData[key] = input.getNextWord(null)
        }

        return mapData
    }
}
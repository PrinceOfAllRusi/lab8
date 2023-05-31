package tools

import multilib.server.dataBase.DataBaseWorker
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.Organization
import organization.OrganizationType


class CreateOrganization: KoinComponent {

    private val dataBaseWorker: DataBaseWorker by inject()

    fun create(data: Map<String, String?>, lastOrganization: Organization?): Organization {

        val org = Organization()

        val name: String
        val annualTurnover: Double
        val employeesCount: Int
        val x: Int
        val y: Long
        val type: OrganizationType
        val street: String
        val zipCode: String
        val userLogin: String

        if (data["name"]!!.isBlank()) {
            name = lastOrganization!!.getName()!!
        } else name = data["name"]!!
        if (data["annualTurnover"]!!.isBlank()) {
            annualTurnover = lastOrganization!!.getAnnualTurnover()!!
        } else annualTurnover = data["annualTurnover"]!!.toDouble()
        if (data["employeesCount"]!!.isBlank()) {
            employeesCount = lastOrganization!!.getEmployeesCount()!!
        } else employeesCount = data["employeesCount"]!!.toInt()
        if (data["x"]!!.isBlank()) {
            x = lastOrganization!!.getCoordinatesX()
        } else x = data["x"]!!.toInt()
        if (data["y"]!!.isBlank()){
            y = lastOrganization!!.getCoordinatesY()
        } else y = data["y"]!!.toLong()
        if (data["type"]!!.isBlank()){
            type = lastOrganization!!.getType()!!
        } else type = OrganizationType.valueOf(data["type"]!!.uppercase())
        if (data["street"]!!.isBlank()){
            street = lastOrganization!!.getPostalAddressStreet()
        } else street = data["street"]!!
        if (data["zipCode"]!!.isBlank()){
            zipCode = lastOrganization!!.getPostalAddressZipCode()
        } else zipCode = data["zipCode"]!!
        if (data["userLogin"]!!.isBlank()) {
            userLogin = lastOrganization!!.getUserLogin()
        } else userLogin = data["userLogin"]!!

        org.setName(name)
        org.setAnnualTurnover(annualTurnover)
        org.setEmployeesCount(employeesCount)
        org.setCoordinatesX(x)
        org.setCoordinatesY(y)
        org.setType(type)
        org.setPostalAddressStreet(street)
        org.setPostalAddressZipCode(zipCode)
        org.setUserLogin(userLogin)

        if (lastOrganization != null) {
            org.setId(lastOrganization.getId())
        } else {
            org.setId(dataBaseWorker.getNextOrgId())
        }

        return org
    }
}

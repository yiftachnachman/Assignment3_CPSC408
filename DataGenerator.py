from faker import Faker
import csv


faker = Faker()


def writeTOCSV():

    with open(fileName, 'w') as csvfile:
        fieldnames = ['customerFirstName','customerLastName','ZIP','city','employeeFirstName','employeeLastName','address','phonenumber']

        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

        writer.writeheader()
        for i in range(int(numTuples)):
            writer.writerow(
                {
                    'customerFirstName': faker.first_name(),
                    'customerLastName': faker.last_name(),
                    'ZIP': faker.zipcode(),
                    'city': faker.city(),
                    'employeeFirstName': faker.first_name(),
                    'employeeLastName': faker.last_name(),
                    'address': faker.address(),
                    'phonenumber': faker.phone_number()
                }
            )


if __name__ == '__main__':

    fileName = raw_input('Enter the name of the file you wish to create: ')
    fileName = fileName + '.csv'
    numTuples = raw_input('Enter the number of tuples you would like: ')
    writeTOCSV()

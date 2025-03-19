#ifndef ADDRESSBOOK_H
#define ADDRESSBOOK_H

#include "person.h"
#include <string>

const int MAX_CONTACTS = 1000; // ͨѶ¼�������

// ͨѶ¼�࣬�ṩ��ϵ�˹�����
class AddressBook {
public:
    AddressBook(); // ���캯��

    void addContact();          // �����ϵ��
    void showContactsByCategory() const; // ��������ʾ��ϵ��
    void deleteContact();       // ɾ����ϵ��
    void findContact() const;   // ������ϵ��
    void modifyContact();       // �޸���ϵ��
    void clearAll();            // ���ͨѶ¼

    bool saveToFile(const std::string& filename) const; // ���浽�ļ�
    bool loadFromFile(const std::string& filename);     // ���ļ�����

private:
    Person contacts[MAX_CONTACTS]; // ��ϵ�˴洢����
    int size;                      // ��ǰ��ϵ������

    bool isContactExist(const std::string& name, const std::string& category) const; // �����ϵ���Ƿ����
};

#endif // ADDRESSBOOK_H
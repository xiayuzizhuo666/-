#include <iostream>
#include <string>
using namespace std;

struct Person {
	string m_Name;    // ����
	string m_Phone;   // �绰
	string m_Addr;    // ��λ
	string m_Category;// ��𣨰칫/���ˣ�
};

#define MAX 1000
struct AddressBook {
	Person personArray[MAX];//ͨѶ¼
	int m_Size = 0;//��ʼ��ͨѶ¼�±꣬һ��ʼΪ��0��
};

AddressBook g_addressBook; // ȫ��ͨѶ¼����(һ������)

void showMenu() {
	cout 
		<< "������ӭ����ͨѶ¼ϵͳv1.0������" << endl
		<< "********************************" << endl
		<< "***********ͨѶ¼ϵͳ***********" << endl
		<< "**********1.�����ϵ��**********" << endl
		<< "**********2.��ʾ��ϵ��**********" << endl
		<< "**********3.ɾ����ϵ��**********" << endl
		<< "**********4.������ϵ��**********" << endl
		<< "**********5.�޸���ϵ��**********" << endl
		<< "**********6.�����ϵ��**********" << endl
		<< "**********0.�˳�ͨѶ¼**********" << endl
		<< "********************************" << endl;
}

void Exit() {
	cout << "��ӭ�´�ʹ�ã�" << endl;
	exit(0);//���������˳�
}
//���
void Add() {
	if (g_addressBook.m_Size >= MAX) {
		cout << "ͨѶ¼�������޷���ӣ�" << endl;
		return;
	}

	Person newPerson;
	cout << "������������";
	cin >> newPerson.m_Name;

	cout << "��������𣨰칫/���ˣ���";
	cin >> newPerson.m_Category;
	if (newPerson.m_Category != "�칫" && newPerson.m_Category != "����") {
		cout << "����������" << endl;//����ж�
		return;
	}

	// ���ͬ������Ƿ����ͬ��
	for (int i = 0; i < g_addressBook.m_Size; i++) {
		if (g_addressBook.personArray[i].m_Name == newPerson.m_Name &&
			g_addressBook.personArray[i].m_Category == newPerson.m_Category) {
			cout << "��������Ѵ��ڸ���ϵ�ˣ�" << endl;
			return;
		}
	}

	cout << "������绰��";
	cin >> newPerson.m_Phone;
	cout << "�����뵥λ��";
	cin >> newPerson.m_Addr;

	g_addressBook.personArray[g_addressBook.m_Size] = newPerson;//����ͨѶ¼
	g_addressBook.m_Size++;
	cout << "��ӳɹ���" << endl;
}
//չʾ
void Show() {
	string category;
	cout << "������Ҫ��ʾ����𣨰칫/���ˣ���";
	cin >> category;

	if (category != "�칫" && category != "����") {
		cout << "������" << endl;
		return;
	}

	bool found = false;//����������addressBook���ҵ����ϵ�person
	for (int i = 0; i < g_addressBook.m_Size; i++) {
		if (g_addressBook.personArray[i].m_Category == category) {
			cout << "������" << g_addressBook.personArray[i].m_Name << endl
				<< "�绰��" << g_addressBook.personArray[i].m_Phone << endl
				<< "��λ��" << g_addressBook.personArray[i].m_Addr << endl
				<< "-------------------" << endl;
			found = true;//����found����ʾ�ҵ���Ӧ��person
		}
	}
	if (!found) cout << "���������ϵ�ˣ�" << endl;
}
//ɾ��
void Delete() {
	string name, category;
	cout << "������Ҫɾ����������";
	cin >> name;
	cout << "��������𣨰칫/���ˣ���";
	cin >> category;
	//����addressBook��Ѱ��person
	int index = -1;
	for (int i = 0; i < g_addressBook.m_Size; i++) {
		if (g_addressBook.personArray[i].m_Name == name &&
			g_addressBook.personArray[i].m_Category == category) {
			index = i;
			break;
		}
	}

	if (index == -1) {
		cout << "��ϵ�˲����ڣ�" << endl;
		return;
	}

	// ǰ������
	for (int i = index; i < g_addressBook.m_Size - 1; i++) {
		g_addressBook.personArray[i] = g_addressBook.personArray[i + 1];
	}
	g_addressBook.m_Size--;
	cout << "ɾ���ɹ���" << endl;
}
//����
void Find() {
	string name;
	cout << "������Ҫ���ҵ�������";
	cin >> name;

	bool found = false;
	for (int i = 0; i < g_addressBook.m_Size; i++) {
		if (g_addressBook.personArray[i].m_Name == name) {
			cout << "������" << g_addressBook.personArray[i].m_Name << endl
				<< "�绰��" << g_addressBook.personArray[i].m_Phone << endl
				<< "��λ��" << g_addressBook.personArray[i].m_Addr << endl
				<< "���" << g_addressBook.personArray[i].m_Category << endl
				<< "-------------------" << endl;
			found = true;
		}
	}
	if (!found) cout << "δ�ҵ�����ϵ�ˣ�" << endl;
}
//�޸�
void Modify() {
	string name, category;
	cout << "������Ҫ�޸ĵ�������";
	cin >> name;
	cout << "������ԭ��𣨰칫/���ˣ���";
	cin >> category;

	int index = -1;
	for (int i = 0; i < g_addressBook.m_Size; i++) {
		if (g_addressBook.personArray[i].m_Name == name &&
			g_addressBook.personArray[i].m_Category == category) {
			index = i;
			break;
		}
	}

	if (index == -1) {
		cout << "��ϵ�˲����ڣ�" << endl;
		return;
	}

	Person &p = g_addressBook.personArray[index];

	cout << "�������µ绰��ԭ��" << p.m_Phone << "����";
	cin >> p.m_Phone;
	cout << "�������µ�λ��ԭ��" << p.m_Addr << "����";
	cin >> p.m_Addr;

	string newCategory;
	cout << "�����������ԭ��" << p.m_Category << "����";
	cin >> newCategory;
	if (newCategory != "�칫" && newCategory != "����") {
		cout << "�������޸�ʧ�ܣ�" << endl;
		return;
	}

	// �����������Ƿ���ͬ��
	if (newCategory != category) {
		for (int i = 0; i < g_addressBook.m_Size; i++) {
			if (i != index && g_addressBook.personArray[i].m_Name == name &&
				g_addressBook.personArray[i].m_Category == newCategory) {
				cout << "��������Ѵ���ͬ����ϵ�ˣ�" << endl;
				return;
			}
		}
	}
	p.m_Category = newCategory;
	cout << "�޸ĳɹ���" << endl;
}
//���
void Clear() {
	g_addressBook.m_Size = 0;//�������
	cout << "ͨѶ¼����գ�" << endl;
}

int main() {
	int select = 0;
	while (true) {
		showMenu();
		cout << "������ѡ��";
		cin >> select;
		switch (select) {
		case 1:
			Add(); break;
		case 2:
			Show(); break;
		case 3:
			Delete(); break;
		case 4:
			Find(); break;
		case 5: 
			Modify(); break;
		case 6:
			Clear(); break;
		case 0:
			Exit(); break;
		default:
			cout << "����������������룡" << endl;
			break;
		}
		system("pause"); // �����������
		system("cls");   // ����
	}
	return 0;
}
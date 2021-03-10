#include <iostream> 
#include <list> 
#include <iterator> 
#include<fstream>
#include<thread>
using namespace std;

list <int> gqlist1, gqlist2; 

class Izvor {
	public:
		virtual void ucitajBroj();
};

class TipkovnickiIzvor: public Izvor {
	public:
		list <int> listaBrojeva;
		
		TipkovnickiIzvor(list<int> numbers): listaBrojeva(numbers){};
		
		virtual list<int>ucitajBroj(){
			int i;
			int kraj = -1;
			
			cout << "Unesite zeljene brojeve (kao zavrsetak unosa unesite -1): ";
			while (cin >> i) {
				if (i == -1) {
					break;
				}
				listaBrojeva.push_back(i);
			}
			return listaBrojeva;
		};
};

class DatotecniIzvor: public Izvor {
	public:
		list<int> listaBrojeva;
		ifstream file;
		
		DatotecniIzvor(ifstream readFile): file(readFile){};
		
		virtual list<int> ucitajBroj() {
			str fileName;
			cout << "Unesite ime zeljene datoteke za ucitavanje brojeva(.txt): ";
			cin >> fileName;
			file.open(fileName);
			
			if (file.is_open()) {
				while (!file.eof()) {


				file >> listaBrojeva;
				}
			}
			file.close();
			return listaBrojeva;
		};
};

class Observer: public SlijedBrojeva{
	public:
		SlijedBrojeva slijed;
		
		Observer(SlijedBrojeva sb): slijed(sb){
			slijed.attach(this);
		};
		
		void update();	
};

class SlijedBrojeva {
	public:
		Izvor izvor;
		list<Observer> observers;
		list<int> listaBrojeva;
		
		SlijedBrojeva(Izvor source): izvor(source){
			listaBrojeva = new list<int>();
			observers = new list<Observer>();
		};
		
		list<int> getNumbers() {
			return listaBrojeva;
		};
		
		list<Observer> getObservers() {
			return observers;
		};
		
		void notify() {
			for (obs : observers) {
				obs.update();
			}
		};
		
		void attach(Observer obs) {
			observers.push_back(obs);
		};
		
		void dettach(Observer obs) {
			observers.remove(obs);
		};
		
		void go() {
			
			listaBrojeva = izvor.ucitajBroj();
			notify();
			this_thread::sleep_for(1s);
			
		};
};

class Datum: public Observer {
	public:
		ofstream zapisnik;
		
		Datum(SlijedBrojeva sb): base
}






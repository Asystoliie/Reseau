#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>
#include <errno.h>  

void manip(){
	int a = 10;
	int b = 25;
	int *p = &b;
	int *pp = &a;

	printf("%s\n", "a = 10, b=25, p = &b, pp = &a");
	printf("&a = %p et &b = %p\n", &a, &b);
	printf("%s : %i\n", "*(&(*(*(&p))))", *(&(*(*(&p)))));
	printf("%s : %i\n", "*(p-1)", *(p-1));
	printf("%s : %i\n", "*(*(&p)-1)", *(*(&p)-1));
	printf("%s : %i\n", "*(*(&pp)+1)",*(*(&pp)+1));
	printf("%s : %i\n", "*(&(*(*(&p))) - 1)", *(&(*(*(&p))) - 1));
}

int* extract (int* tableau, int n, int a, int b, int* new_n){

	int save = 0, pos = 0;

	for(int i = 0; i < n; i++){
		if(tableau[i] >= a && tableau[i] <= b)
			save++;
	}

	int* new_tab = malloc(save * sizeof(int));
	*new_n = save;

	for(int i = 0; i < n; i++){
		if(tableau[i] >= a && tableau[i] <= b){
			new_tab[pos] = tableau[i];
			pos++;
		}
	}

	return new_tab;
}

int sum(int* tableau, int n){

	if(n == 0)
		return 0;
	else
		return tableau[n-1] + sum(tableau, n - 1);
}	

void createFork(int largeur, int hauteur)
{
	if (hauteur == 0)
	{
		return;
	}

	for (int i = 0; i < largeur; i++)
	{
		switch(fork())
	   	{
	   		case -1:
				printf("%s\n", "Erreur fork");
				break;
			case 0: //fils
				createFork(largeur, hauteur-1);
				sleep(1000);
				break;
			default: //père
				printf("pid = %d, pid père = %d\n", getpid(), getppid());
				break;
	   	}
	}

	wait(0);
}


int main(){

	manip(); //question 1
	printf("\n");



	int n = 10, a = 3, b = 7; // question 2
	int* tableau = malloc(n * sizeof(int));
	for(int i = 0; i < n; i++)
		tableau[i] = i;
	int size;
	int* test;
	test = extract(tableau, n , a , b, &size);

	for(int i = 0; i < n; i++)
		printf("%i ", tableau[i]);
	printf("\n");

	for(int i = 0; i < size; i++)
		printf("%i ", test[i]);
	printf("\n");



	/*printf("taille = ?\n"); //question 3
	size = 0;
	scanf("%i", &size);

	int* tab = malloc(size * sizeof(int));

	for(int i = 0 ; i < size; i++){
		printf("[%i] :", i);
		scanf("%i", &tab[i]);
	}

	for(int i = 0; i < size; i++)
		printf("%i ", tab[i]);
	printf("\n");

	printf("somme = %i\n", sum(tab, size));*/


	//createFork(4, 1); //question 4

	

	/*int nb_tube = 4;
	int** matrice = malloc(nb_tube * sizeof(int*));

	for(int i = 0; i < nb_tube; i++){
		matrice[i] = malloc(2 * sizeof(int));
	}

	for(int i = 0; i < nb_tube; i++){
		if(pipe(matrice[i]) == -1)
		{
			printf("Erreur tube: %s\n", strerror(errno));
			exit(1);
		}
	}*/
	
	printf("\n");
	int tube[2];
	if(pipe(tube) == -1)
	{
		printf("Erreur lors de la création du tube : %s\n", strerror(errno));
		exit(1);
	}

	int TAILLE_MESSAGE = 100;
	char messageLire[TAILLE_MESSAGE], messageEcrire[TAILLE_MESSAGE];

	printf("Votre message ?\n");
	scanf("%s", messageEcrire);
	printf("\n");

	switch(fork())
   	{
   		case -1:
			printf("%s\n", "Erreur fork");
			break;
		case 0: //fils

			close(tube[1]);
			int octets_lu = 0;
			if((octets_lu = read(tube[0], messageLire, sizeof(messageLire))) != 0)
			{
				printf("fils -> %i octets lu : %s\n", octets_lu, messageLire);
			}
			printf("\n");
			sleep(1000);
			break;
		default: //père

			close(tube[0]);
			write(tube[1], messageEcrire, sizeof(messageEcrire));
			break;
   	}

	return 0;
}
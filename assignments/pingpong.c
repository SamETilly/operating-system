#include<stdio.h>
#include<sys/time.h>
#include<sys/wait.h>
#include<unistd.h>
#include<stdlib.h>

int main(void){
	int p1[2];
	int p2[2];
	int n = 10000;
	struct timeval start, end;

	pipe(p1);
	pipe(p2);

	pid_t pid = fork();

	if(pid < 0){
		printf("Fail\n");
		exit(1);
	}
	else if(pid == 0){
		char b;
		for(int i = 0; i < n; i++){
			read(p1[0], &b, 1);
			write(p2[1], &b, 1);
		}
		close(p1[1]);
		close(p2[0]);
		exit(0);
	}
	else{
		char x = 'x';

		gettimeofday(&start, NULL);
		for(int i = 0; i < n; i++){
			write(p1[1], &x, 1);
			read(p2[0], &x, 1);
		}
		gettimeofday(&end, NULL);
		close(p1[0]);
		close(p2[1]);
		printf("%ld %ld %ld %ld\n", start.tv_sec, start.tv_usec, end.tv_sec, end.tv_usec);
		double elapsed = (end.tv_sec - start.tv_sec) + (end.tv_usec - start.tv_usec) / 1000000.0;
		double exchanges = n / elapsed;

		printf("Exchanges per second: %f\n", exchanges);
		wait(NULL);

	}


	return 0;
}

pipeline{
    agent any

    environment {
       BACK_CONTAINER_NAME="RENIORS_BACK"
       BACK_NAME = "RENIORS_BACK"

       FRONT_CONTAINER_NAME="RENIORS_FRONT"
       FRONT_NAME = "RENIORS_FRONT"
    }
    stages {
        stage('Clean'){
            steps{
                script {
                    try{
                        sh "docker stop ${BACK_CONTAINER_NAME}"
                        sleep 1
                        sh "docker rm ${BACK_CONTAINER_NAME}"
                        sleep 1
                        sh "docker stop ${BACK_CONTAINER_NAME}"
                        sleep 1
                        sh "docker rm ${BACK_CONTAINER_NAME}"
                    }catch(e){
                        sh 'exit 0'
                    }
                }
            }
        }
        stage('Build') {
            steps {
                sh "docker build -t ${NAME} ."
            }
        }
        stage('Deploy'){
            steps {
                sh "docker tag ${NAME}:latest ${NAME}:${VERSION}"
                sh "docker run -d --name=${CONTAINER_NAME} -p 8080:8080 ${NAME}:latest"
            }
        }
    }
}
출처: https://ongamedev.tistory.com/472 [가끔 보자, 하늘.:티스토리]
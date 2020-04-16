def JDK_HOME
def PROPS
def GIT_URL
/* def CI_TOOLS_BRANCH */
def IS_BRANCH
def GIT_CREDENTIALS_ID
def JENKINS_WS
def IS_REPO_HOME
def SAG_HOME
def ABE_HOME
def BUILD_VERSION
def DEPLOYER_HOME
def DEPLOYER_HOST
def DEPLOYER_PORT
def DEPLOYER_USER
def DEPLOYER_PWD
def PROJECT_NAME_IS
def DEP_SET_IS
def DEP_MAP_IS
def DEP_CAN_IS
def REPO_NAME="MyUtilities"
def TARGET_ALIAS_IS
def TARGET_HOST_IS
def TARGET_PORT_IS
def TARGET_USER_IS
def TARGET_PWD_IS
def TARGET_VERSION_IS

def ASSETS_PREFIX_IS
def DIRECTORY_TEST_REPORTS

pipeline {
	environment {
		SAG_HOME="C:/SoftwareAG"
	}
	
	agent any
	
	stages {
		stage('Set Environment') {

			steps{
				print " ----- Set Environment ----- "
				script {
						PROPS = readProperties file: 'properties/Jenkinsfile.properties'
						GIT_URL=PROPS['GIT_URL']
						/* CI_TOOLS_BRANCH=PROPS['CI_TOOLS_BRANCH'] */
						GIT_CREDENTIALS_ID=PROPS['GIT_CREDENTIALS_ID']
						JENKINS_WS=PROPS['JENKINS_WS']
						IS_REPO_HOME=PROPS['IS_REPO_HOME']
						IS_BRANCH=PROPS['IS_BRANCH']
						BUILD_VERSION=PROPS['BUILD_VERSION']
						SAG_HOME=PROPS['SAG_HOME']
						ABE_HOME=PROPS['ABE_HOME']
						DEPLOYER_HOME=PROPS['DEPLOYER_HOME']
						DEPLOYER_HOST=PROPS['DEPLOYER_HOST']
						DEPLOYER_PORT=PROPS['DEPLOYER_PORT']
						DEPLOYER_USER=PROPS['DEPLOYER_USER']
						DEPLOYER_PWD=PROPS['DEPLOYER_PWD']
						PROJECT_NAME_IS=PROPS['PROJECT_NAME_IS']+"_"+"${BUILD_VERSION}"
						DEP_SET_IS=PROPS['DEP_SET_IS']
						DEP_MAP_IS=PROPS['DEP_MAP_IS']
						DEP_CAN_IS=PROPS['DEP_CAN_IS']
						TARGET_ALIAS_IS=PROPS['TARGET_ALIAS_IS']
						TARGET_HOST_IS=PROPS['TARGET_HOST_IS']
						TARGET_PORT_IS=PROPS['TARGET_PORT_IS']
						TARGET_USER_IS=PROPS['TARGET_USER_IS']
						TARGET_PWD_IS=PROPS['TARGET_PWD_IS']
						TARGET_VERSION_IS=PROPS['TARGET_VERSION_IS']
						
						PKG_PREFIX=PROPS['ASSETS_PREFIX_IS']

						/*	ASSETS_PREFIX_IS=PROPS['ASSETS_PREFIX_IS'] */
						DIRECTORY_TEST_REPORTS=PROPS['DIRECTORY_TEST_REPORTS']	

						echo "*****************************************************1"
						echo "$GIT_URL"
						echo "*****************************************************1"
				}
				echo "*****************************************************2"
				echo "$GIT_URL"
				echo "*****************************************************2"
			}		
		}
			
		
		stage ('Checkout SourceCode') {				
			steps {
					print " ----- SourceCode Checkout -----"
				script {
					GIT_CREDENTIALS_ID=PROPS['GIT_CREDENTIALS_ID']					
				}
				gitCheckout("${JENKINS_WS}/source/${IS_REPO_HOME}", "${IS_BRANCH}", "${GIT_CREDENTIALS_ID}", "${GIT_URL}")
				
			}
			}
		
		
		stage ('Create Build') {				
			steps {
					print " ----- Create Build ----- "
				createBuild("${SAG_HOME}","${ABE_HOME}","${BUILD_VERSION}","${JENKINS_WS}")
				
			}
		}



		stage ('Deploy Build') {				
			steps {
					print " ----- Create Project ----- "
				createProject("${SAG_HOME}", "${ABE_HOME}", "${SAG_HOME}/${DEPLOYER_HOME}", "${BUILD_VERSION}", "C:/Demo/CICD/CIScripts/ProjectAutomatorIS.xml", "C:/Demo/CICD/CIScripts/ProjectAutomatorIS.tpl", "${DEPLOYER_HOST}", "${DEPLOYER_PORT}", "${DEPLOYER_USER}", "${DEPLOYER_PWD}", "${PROJECT_NAME_IS}", "${DEP_SET_IS}", "${DEP_MAP_IS}", "${DEP_CAN_IS}", "${REPO_NAME}", "${JENKINS_WS}/build/is", "${TARGET_ALIAS_IS}", "${TARGET_HOST_IS}", "${TARGET_PORT_IS}", "${TARGET_USER_IS}", "${TARGET_PWD_IS}", "${TARGET_VERSION_IS}","${PKG_PREFIX}")
				

					print " ----- Deploy Build ----- "
				deployBuild("${SAG_HOME}/${DEPLOYER_HOME}","${PROJECT_NAME_IS}","${DEP_CAN_IS}","${DEPLOYER_HOST}", "${DEPLOYER_PORT}", "${DEPLOYER_USER}", "${DEPLOYER_PWD}")
				
				
			}
		}					


		}
	}
	


def gitCheckout(branchDir, branchName, credentialsId, gitRepoURL) {
	dir ("$branchDir") {
		echo "$branchName"
		echo "$credentialsId"
		echo "$gitRepoURL"
       	git branch: "$branchName", credentialsId: "$credentialsId", url: "$gitRepoURL"
    }
}


def createBuild(sagHome, abeHome, buildVersion, ciWorkspace) {
	bat "$abeHome/bin/build.bat \
		-Dsag.install.dir=$sagHome \
        -Dbuild.source.dir=$ciWorkspace/source/is \
        -Dbuild.output.dir=$ciWorkspace/build/is \
        -Dbuild.version=$buildVersion \
        -Dbuild.log.fileName=$ciWorkspace/logs/log_" + "$buildVersion" + ".txt \
        -Denable.build.IS=true \
        -Denable.archive=true"
	
	}



def createProject(sagHome, abeHome, deployerHome, buildVersion, projectAutomatorFile, projectAutomatorTemplate, deployerHost, deployerPort, deployerUser, deployerPwd, projName, depSetName, depMapName, depCanName, deployerRepoName, deployerRepoPath, serverAlias, serverHost, serverPort, user, pwd, version, assetPrefix) {

		echo "sagHome: $sagHome"
		echo "abeHome: $abeHome"
		echo "deployerHome: $deployerHome"
		echo "buildVersion: $buildVersion"
		echo "projectAutomatorFile: $projectAutomatorFile"
		echo "projectAutomatorTemplate: $projectAutomatorTemplate"
		echo "deployerHost: $deployerHost"
		echo "deployerPort: $deployerPort"
		echo "deployerUser: $deployerUser"
		echo "deployerPwd: $deployerPwd"
		echo "projName: $projName"
		echo "depSetName: $depSetName"
		echo "depMapName: $depMapName"
		echo "depCanName: $depCanName"
		echo "deployerRepoName: $deployerRepoName"
		echo "deployerRepoPath: $deployerRepoPath"
		echo "serverAlias: $serverAlias"
		echo "serverHost: $serverHost"
		echo "serverPort: $serverPort"
		echo "user: $user"
		echo "pwd: $pwd"
		echo "version: $version"
		echo "assetPrefix: $assetPrefix"
		

				bat "$sagHome/common/lib/ant/bin/ant.bat -file C:/Demo/CICD/CIScripts/build.xml createProjectReposiotry \
				-Dautomator.file=$projectAutomatorFile \
				-Dautomator.file.tpl=$projectAutomatorTemplate \
				-Ddeployer.home=$deployerHome \
				-Ddeployer.host=$deployerHost \
				-Ddeployer.port=$deployerPort \
				-Ddeployer.user=$deployerUser \
				-Ddeployer.pwd=$deployerPwd \
				-Dproj_repository.name=$deployerRepoName \
				-Dproj_repository.path=$deployerRepoPath \
				-Dis_proj.name=$projName \
				-Dis_depset.name=$depSetName \
				-Dis_depmap.name=$depMapName \
				-Dis_depcan.name=$depCanName \
				-Dis_target.alias=$serverAlias \
				-Dis_target.host=$serverHost \
				-Dis_target.port=$serverPort \
				-Dis_target.user=$user \
				-Dis_target.pwd=$pwd \
				-Dis.version=$version \
				-Dis_composite.name.filter=$assetPrefix"				

}



def deployBuild(deployerHome, projName, depCanName, deployerHost, deployerPort, deployerUser, deployerPwd) {
	bat "$deployerHome/bin/Deployer.bat --deploy -project $projName -dc $depCanName -host $deployerHost -port $deployerPort -user $deployerUser -pwd $deployerPwd"
}




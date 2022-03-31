const puppeteer = require('puppeteer');
(async () => {
	const browser = await puppeteer.launch();
	const page = await browser.newPage();
	try {
		await page.goto(
			'https://8081-dbcebacafbefccbefceeaecbbfabeaecfaaccbd.examlyiopb.examly.io/'
		);
		await page.setViewport({
			width: 1200,
			height: 800,
		});
		await page.click('#signupLink');
		await page.waitForSelector('#signupBox', { timeout: 3000 });
		await page.type('#email', 'test@gmail.com');
		await page.type('#name', 'testuser');
		await page.type('#mobileNumber', '9876543210');
		await page.type('#password', 'Test@123');
		await page.type('#confirmPassword', 'Test@123');
		await page.click('#submitButton');
		await page.waitForNavigation();
		await page.waitForSelector('#login', { timeout: 3000 });
		console.log('TESTCASE:FE_Signup:success');
		await login();
	} catch (e) {
		console.log('TESTCASE:FE_Signup:failure');
	} finally {
		await page.close();
		await browser.close();
	}
})();

const login = async () => {
	const browser = await puppeteer.launch();
	const page = await browser.newPage();
	try {
		await page.goto(
			'https://8081-dbcebacafbefccbefceeaecbbfabeaecfaaccbd.examlyiopb.examly.io/'
		);
		await page.setViewport({
			width: 1200,
			height: 800,
		});
		await page.type('#email', 'test@gmail.com');
		await page.type('#password', 'Test@123');
		await page.click('#loginButton');
		await page.waitForNavigation();
		await page.waitForSelector('#userHomeBox', { timeout: 3000 });
		await page.click('#profileButton');
		await page.waitForSelector('#userProfileBox', { timeout: 3000 });
		console.log('TESTCASE:FE_login_userPage:success');
	} catch (e) {
		console.log('TESTCASE:FE_login_userPage:failure');
	} finally {
		await page.close();
		await browser.close();
	}
};

(async () => {
	const browser = await puppeteer.launch();
	const page = await browser.newPage();
	try {
		await page.goto(
			'https://8081-dbcebacafbefccbefceeaecbbfabeaecfaaccbd.examlyiopb.examly.io/'
		);
		await page.setViewport({
			width: 1200,
			height: 800,
		});
		await page.type('#email', 'admin@gmail.com');
		await page.type('#password', 'Admin@123');
		await page.click('#loginButton');
		await page.waitForNavigation();
		await page.waitForSelector('#adminHome', { timeout: 3000 });
		console.log('TESTCASE:FE_adminPage:success');
	} catch (e) {
		console.log('TESTCASE:FE_adminPage:failure');
	} finally {
		await page.close();
		await browser.close();
	}
})();

(async () => {
	const browser = await puppeteer.launch();
	const page = await browser.newPage();
	try {
		await page.goto(
			'https://8081-dbcebacafbefccbefceeaecbbfabeaecfaaccbd.examlyiopb.examly.io/'
		);
		await page.setViewport({
			width: 1200,
			height: 800,
		});
		await page.type('#email', 'admin@gmail.com');
		await page.type('#password', 'Admin@123');
		await page.click('#loginButton');
		await page.waitForNavigation();
		await page.waitForSelector('#adminHome', { timeout: 3000 });
		await page.click('#routeButton');
		await page.waitForSelector('#adminRoute', { timeout: 3000 });
		await page.click('#logout');
		await page.waitForSelector('#login', { timeout: 3000 });
		console.log('TESTCASE:FE_adminRoute:success');
	} catch (e) {
		console.log('TESTCASE:FE_adminRoute:failure');
	} finally {
		await page.close();
		await browser.close();
	}
})();

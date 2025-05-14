# Manual Test Cases

This document contains manual test cases based on Create Wallet functionality. Each test case includes a summary, steps, and expected results.

---

## 1. Create New Wallet without additional configuration
**Related Automated Test:** `CreateNewWalletTest.CreateWalletTest`

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Skip "Enable Face ID".
7. Skip "Enable notifications".
8. Skip "Buy Crypto/Deposit Crypto".
9. Skip "What's New" if such pop up is present. Click "X".

**Expected Result:**
- The wallet is created successfully.
- The main screen is loaded.
- The default total amount is displayed as `$0.00` and `$0.00 (0.00%)` on the main screen.
- "Your wallet is empty" is displayed on the main screen.
- "Buy" tab is selected by default.
- "Crypto" section is selected by default.

---

## 2. Create New Wallet with additional configuration

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Tap "Enable Face ID". Click Allow.
7. Tap "Enable notifications". Click Allow.
8. For "Buy Crypto/Deposit Crypto", Tap "Skip, I'll do it later".
9. Skip "What's New" if such pop up is present. Click "X".
10. CLick "Main Wallet" on the top.
11. Click "Configuration" icon from the right corner.
12. Tap "Notifications".
13. Tap Back button.
14. Tap Security. Check that Face ID was applied.
15. Lock mobile app.
16. Unlock mobile app.

**Expected Result:**

**Step 9:**
- The wallet is created successfully.
- The main screen is loaded.
- The default total amount is displayed as `$0.00` on the main screen.

**Step 12:**
- The "Notifications" screen is opened.
- Notifications are enabled.

**Step 14:**
- Lock method: Passcode / Face ID is enabled.
**Step 16:**
- Possible to unlock the app using passcode or Face ID.

---

## 3. Create New Wallet without Internet

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Switch OFF the internet on the phone.
4. Tap on "Create New Wallet" button.
5. Create Passcode (for example 1-2-3-4-5-6).
6. Confirm Passcode (for example 1-2-3-4-5-6).
7. Skip "Enable Face ID".
8. Skip "Enable notifications".
9. Skip "Buy Crypto/Deposit Crypto".

**Expected Result:**
- The wallet is created successfully.
- The main screen is loaded.
- if click to "Buy crypto" button -> "Something went wrong... Try again"
Same should be on all places where user plan to do any actions with crypto. For example if you go to search and tap to 
any crypto - should be same pop up that NO internet.

**BUGs HERE**
1. If go to Search and click to any crypto - App is crashed and closed. 
Same is if click "Manage crypto" and select any crypto on the next screen.
2. If go to NFT section -> "Retry" text is NOT visible in the button.
---

## 4. Create New Wallet with closing app in the middle of process

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Close app process.
7. Open installed app again

**Expected Result:**
- First screen with "Create new wallet" button is displayed.
Same need to validate for call/message during os such process.
---

## 5. Create New Wallet and hide app view on Face ID configuration

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Hide the app (don't finish app process, just open another app).
7. Open TrustWallet app again

**Expected Result:**
- Enter passcode screen is displayed.

**BUG HERE**
Pop up with allow FACE ID is opened automatically under Enter passcode screen, 
if click "Allow" you are redirected to Setup Face ID screen again (but you already allowed this). 
Need to validate requirements related with it.
---

## 6. Create New Wallet and hide app view on "Enable Notifications" configuration

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Tap "Enable Face ID". Click Allow.
6. Hide the app (don't finish app process, just open another app).
7. Open TrustWallet app again

**Expected Result:**
- Enable notifications screen is displayed.

---

## 7. Create New Wallet and Buy Crypto in the end of creation process

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Skip "Enable Face ID".
7. Skip "Enable notifications".
8. Tap "Buy Crypto".

**Expected Result:**
- The wallet is created.
- "Buy crypto" screen is opened".
- "BTC" is selected by default.
- Possibility to buy using Apple Pay is present

---

## 8. Create New Wallet and Deposit Crypto in the end of creation process

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Skip "Enable Face ID".
7. Skip "Enable notifications".
8. Tap "Deposit Crypto".

**Expected Result:**
- The wallet is created.
- "Deposit crypto" screen is opened".
- "All networks" is selected by default in drop down.
- "Popular" section is on the top, next is "All crypto"

---

## 9. Invalid Passcode Validation
**Related Automated Test:** `InvalidPassCodeValidationTest.PassCodeValidationTest`

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Enter a 6-digit passcode (e.g., 1-2-3-4-5-6).
5. When prompted to confirm, enter a different 6-digit passcode (e.g., 2-3-4-5-6-7).

**Expected Result:**
- A validation message appears indicating that the passcodes do not match.
- After validation user should put Create passcode again.
- Validate if user puts not 6 digits for Confirm passcode -> no validation until user will not put 6.

---

## 10. Search Crypto Function - Positive test
**Related Automated Test:** `SearchCryptoTest.SearchCryptoFunctionTest`

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Tap "Enable Face ID". Click Allow.
7. Tap "Enable notifications". Click Allow.
8. For "Buy Crypto/Deposit Crypto", Tap "Skip, I'll do it later".
9. Skip "What's New" if such pop up is present. Click "X".
10. Tap to the "Search" icon on the right corner.
11. Enter a search term (e.g., "BTC", that is visible on the first search result).
12. Wait for the search results to load.

**Expected Result:**
- All search result items contain the search value (e.g., "BTC").

---

## 11. Search Crypto Function - Negative test

**Steps:**
1. Download and Install application on Android/iOS from "https://trustwallet.com/"
2. Open installed application.
3. Tap on "Create New Wallet" button.
4. Create Passcode (for example 1-2-3-4-5-6).
5. Confirm Passcode (for example 1-2-3-4-5-6).
6. Tap "Enable Face ID". Click Allow.
7. Tap "Enable notifications". Click Allow.
8. For "Buy Crypto/Deposit Crypto", Tap "Skip, I'll do it later".
9. Skip "What's New" if such pop up is present. Click "X".
10. Tap to the "Search" icon on the right corner.
11. Enter random letters/digits to the search input.

**Expected Result:**
- "No data found." message is displayed.

---
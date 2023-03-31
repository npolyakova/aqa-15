#Pet updating
  Feature: User created a pet and wants to update its info

    Scenario:
      Given user created a pet
      When user save updated pet info
      Then pet info is updated
import {ParkingManagementSystemFrontendPage} from './app.po';

describe('parking-management-system-frontend App', () => {
  let page: ParkingManagementSystemFrontendPage;

  beforeEach(() => {
    page = new ParkingManagementSystemFrontendPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});

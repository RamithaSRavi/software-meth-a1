/**
 * The class that stores and organizes all gym member data.
 * More description
 * @author Ramitha Ravishankar, Gloria Liu
 */

public class MemberDatabase {
    private Member [] mlist;
    private int size;
    private static final int NOT_FOUND = -1;

    /**
     * Default constructor to instantiate a MemberDatabase object with 4 empty spots..
     */
    public MemberDatabase() {
        this.mlist = new Member[4];
        this.size = 0;
    }

    /**
     * Gets the member list of the MemberDatabase object.
     * @return the MemberDatabase object's member list array.
     */
    public Member[] getMlist() {
        return this.mlist;
    }


    /**
     * Finds location of member in mlist
     * @param member of type Member to locate
     * @return index of member in mlist if found, -1 if not found
     */
    private int find(Member member) {
        Member[] memberList = this.mlist;
        String fname = member.getFname();
        String lname = member.getLname();
        Date dob = member.getDob();
        for(int i=0; i<memberList.length; i++) {
            if(memberList[i].getFname().equals(fname) && memberList[i].getLname().equals(lname) && memberList[i].getDob().compareTo(dob) == 0) return i;
        }
        return NOT_FOUND;
    }

    /**
     * Finds location of member in mlist
     * @param member of type Member to locate
     * @return index of member in mlist if found, -1 if not found
     */
    //TODO: check if its okay to make another public method so it can be called in gym manager
    public int findMember(Member member) {
        Member[] memberList = this.mlist;
        String fname = member.getFname();
        String lname = member.getLname();
        Date dob = member.getDob();
        for(int i=0; i<memberList.length; i++) {
            if(memberList[i].getFname().equals(fname) && memberList[i].getLname().equals(lname) && memberList[i].getDob().compareTo(dob) == 0) return i;
        }
        return NOT_FOUND;
    }

    /**
     * Increases the capacity of mlist by 4 because mlist is full
     */
    private void grow() {
        Member[] currentList = this.mlist;
        Member[] newList = new Member[currentList.length + 4];
        for(int i=0; i< currentList.length; i++) {
            newList[i] = currentList[i];
        }
        this.mlist = newList;
    }

    /**
     * Adds member to the end of mlist
     * @param member of type Member to be added
     * @return true if member is added to mlist, false if member already exists
     */
    public boolean add(Member member) {
        Member[] memberList = this.mlist;

        if(find(member) != NOT_FOUND) return false;

        for(int i=0; i<memberList.length; i++) {
            if(memberList[i] == null) {
                memberList[i] = member;
                if(i+1 >= memberList.length) {
                    this.grow();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Removes member from mlist and retains order of existing members
     * @param member of type Member to be removed
     * @return true if member is removed, false if member does not exist
     */
    public boolean remove(Member member) {
        Member[] memberList = this.mlist;
        int index = find(member);
        if(index == -1) return false;
        memberList[index] = null;
        size--;

        // Shift all members left in the member list
        while(index < memberList.length-1) {
            if(memberList[index+1] != null) {
                memberList[index] = memberList[index+1];
                if(index == memberList.length-2) {
                    memberList[index+1] = null;
                }
            } else {
                memberList[index] = null;
                break;
            }
        }

        return true;
    }

    /**
     * Prints existing members in mlist
     */
    public void print() {
        Member[] memberList = this.mlist;
        for(int i=0; i< memberList.length; i++) {
            if(memberList[i] != null) System.out.println(memberList[i]);
        }
    }

    /**
     * Sorts and prints members alphabetically by county name and then zip code
     */
    public void printByCounty() {
        Member[] memberList = this.mlist;

        for(int i=0; i< memberList.length-1; i++) {
            Member.Location member1Location = memberList[i].getLocation();
            int minimum = i;
            for(int j=i+1; j< memberList.length; j++) {
                Member.Location member2Location = memberList[j].getLocation();
                String member1County = member1Location.getCounty();
                String member2County = member2Location.getCounty();
                if(member1County.compareTo(member2County) == 0 ) {
                    //TODO: check if comparison
                    if(member1Location.getZip().compareTo(member2Location.getZip()) < 0) {
                        minimum = j;
                    }
                } else if (member1County.compareTo(member2County) > 0) {
                    minimum = j;
                }
            }

            Member temp = memberList[i];
            memberList[i] = memberList[minimum];
            memberList[minimum] = temp;
        }

        this.print();
    }

    /**
     * Sorts and prints members in ascending order of expiry date
     */
    public void printByExpirationDate() {
        Member[] memberList = this.mlist;

        for(int i=0; i< memberList.length-1; i++) {
            Member member1 = memberList[i];
            int minExpiryDate = i;
            for(int j=i+1; j< memberList.length; j++) {
                Member member2 = memberList[j];
                int compare = member2.getExpire().compareTo(member1.getExpire());
                if(compare > 0 ) {
                    minExpiryDate = j;
                }
            }

            Member temp = memberList[i];
            memberList[i] = memberList[minExpiryDate];
            memberList[minExpiryDate] = temp;
        }

        this.print();
    }

    /**
     * Sorts and prints members alphabetically by last name and then first name
     */
    public void printByName() {
        Member[] memberList = this.mlist;

        for(int i=0; i< memberList.length-1; i++) {
            int minimum = i;
            Member member1 = memberList[i];
            for(int j=i+1; j< memberList.length; j++) {
                Member member2 = memberList[j];
                int compare = member2.compareTo(member1);
                if(compare > 0 ) {
                    minimum = j;
                }
            }

            Member temp = memberList[i];
            memberList[i] = memberList[minimum];
            memberList[minimum] = temp;
        }

        this.print();
    }
}